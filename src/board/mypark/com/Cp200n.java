package board.mypark.com;

import java.awt.Color;

import javax.swing.JOptionPane;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import serialException.mypark.com.ReadDataFromSerialPortFailure;
import serialException.mypark.com.SerialPortInputStreamCloseFailure;
import serialException.mypark.com.NoSuchPort;
import serialException.mypark.com.NotASerialPort;
import serialException.mypark.com.PortInUse;
import serialException.mypark.com.SerialPortParameterFailure;
import serialException.mypark.com.TooManyListeners;
import serialException.mypark.com.SerialTool;


public class Cp200n {
	private int [] RecvDataBuf = new int [65536];	//�������ݻ�����
	private int [] SendDataBuf = new int [65536];	//�������ݻ�����
	private int RecvAddIndex;						//��������ָ��
	private int RecvDoneIndex;						//���ݴ���ָ��
	private int SendAddIndex;						//��������ָ��
	private int SendDoneIndex;						//���ݴ���ָ��
	private String BoardIpAddress;					//���ư��IP��ַ
	private int RecvFc1;							//����Э��Ĺ�����1
	private int RecvFc2;							//����Э��Ĺ�����2
	private int RecvCrc1;							//����Э���У����1
	private int RecvCrc2;							//����Э���У����2
	private int RecvCrc;							//����Э���У����
	private static int RecvHead = 3;				//����Э���Э��ͷ
	private static int RecvEnd = 4;					//����Э���Э��β
	private static int RecvEtx = 13;				//����Э������ݽ�����
	private int SendFc1;							//����Э��Ĺ�����1
	private int SendFc2;							//����Э��Ĺ�����2
	private int SendCrc;							//����Э���У����
	private static int SendHead = 1;				//����Э���Э��ͷ
	private static int SendEnd = 2;					//����Э���Э��β
	private static int SendEtx = 13;				//����Э������ݽ�����
	private int WaitingTime;						//�������ݺ�ȴ�Ӧ���ʱ�䣬����Ϊ0ʱ������ȴ�Ӧ��
	private boolean ConnectedFlag;					//���ư������ӱ�־
	private int ConnectMethod;						//���ӷ�ʽ
	private SerialPort serialPort = null;			//���洮�ڶ���
	private boolean SerialPortOpenedFlag;			//�����Ѵ򿪱�־λ
	
	public Cp200n() {								//���캯��
		RecvAddIndex = 0;							//���ָ��
		RecvDoneIndex = 0;							//���ָ��
		SendAddIndex = 0;							//���ָ��
		SendDoneIndex = 0;							//���ָ��
		SerialPortOpenedFlag = false;				//
	}
	/* ���뷢������ͷ */
	private void getSendDataHead(int fc1, int fc2) {
		if(SendAddIndex == SendDoneIndex) {
			SendDoneIndex = 0;
			SendAddIndex = 0;
		}
		SendCrc = 0;
		SendDataBuf[SendAddIndex++] = SendHead;
		SendCrc ^= SendHead;
		SendDataBuf[SendAddIndex++] = fc1;
		SendCrc ^= fc1;
		SendDataBuf[SendAddIndex++] = fc2;
		SendCrc ^= fc2;
	}
	/* ���뷢������β */
	private void getSendDataEnd() {
		int crc1 = 0;
		int crc2 = 0;
		SendDataBuf[SendAddIndex++] = SendEtx;
		SendCrc ^= SendEtx;
		crc1 = (SendCrc >>> 4) | 0x00000030;
		crc2 = (SendCrc & 0xffffff0f) | 0x00000030;
		SendDataBuf[SendAddIndex++] = crc1;
		SendDataBuf[SendAddIndex++] = crc2;
		SendDataBuf[SendAddIndex++] = SendEnd;
	}
	
	/* ����ͨѶ��ʽ�����ӷ����������巽���У�����ͨѶ��TCP/IP��7098SYͨѶת����7182SYͨѶת�� */
	public void SetConnectMethod(int method) {
		if(ConnectMethod == 0) {					//ͨѶ��ʽ0=����ͨѶ
			if(SerialPortOpenedFlag) {
				SerialTool.closePort(serialPort);
				SerialPortOpenedFlag = false;
			}
		}
		else if(ConnectMethod == 1) {				//ͨѶ��ʽ1=TCP/IPͨѶ
			
		}
		else if(ConnectMethod == 2) {				//ͨѶ��ʽ2=7182/7183ͨѶת��
			
		}
		else if(ConnectMethod == 3) {				//ͨѶ��ʽ3=7098ͨѶת��
			
		}
		ConnectMethod = method;
	}
	
	/* �����Ӷ˿ڣ��������ӿ��ư壩���������sΪIP��ַ�򴮿ں�
	 * ����ֵ= 0��ִ�гɹ�
	 * ����ֵ=-1������Ĵ��ں�
	 * ����ֵ=-2������Ĵ��ڲ�����
	 * ����ֵ=-3����д���ַ�����һ�����ں�/�����ڵĴ��ں�/�ô����ѱ�ռ��/�ô����ѱ����� 
	 * ����ֵ=-4�������ظ��� */
	public int SetConnected(String s) {
		if(ConnectMethod == 0) {					//ͨѶ��ʽ0=����ͨѶ
			if(SerialPortOpenedFlag) return -4;
			String commName = s;//��ȡ��������			
			String bpsStr = "115200";//��ȡ������
			if (commName == null || commName.equals("")) {//��鴮�������Ƿ��ȡ��ȷ
				//JOptionPane.showMessageDialog(null, "û����������Ч���ڣ�", "����", JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
			if (bpsStr == null || bpsStr.equals("")) {//��鲨�����Ƿ��ȡ��ȷ
				//JOptionPane.showMessageDialog(null, "�����ʻ�ȡ����", "����", JOptionPane.INFORMATION_MESSAGE);
				return -2;
			}
			int bps = Integer.parseInt(bpsStr);//�������������ʾ���ȡ��ȷʱ
			try {
				serialPort = SerialTool.openPort(commName, bps);//��ȡָ���˿����������ʵĴ��ڶ���
				SerialTool.addListener(serialPort, new SerialListener());//�ڸô��ڶ�������Ӽ�����
				SerialPortOpenedFlag = true;
				//�����ɹ�������ʾ
				//JOptionPane.showMessageDialog(null, "�����ɹ����Ժ���ʾ������ݣ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				//�����Ѵ�
			
			} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse | TooManyListeners e1) {
				//��������ʱʹ��һ��Dialog��ʾ����Ĵ�����Ϣ
				//JOptionPane.showMessageDialog(null, e1, "����", JOptionPane.INFORMATION_MESSAGE);
				return -3;
			}
		}
		else if(ConnectMethod == 1) {				//ͨѶ��ʽ1=TCP/IPͨѶ
			
		}
		else if(ConnectMethod == 2) {				//ͨѶ��ʽ2=7182/7183ͨѶת��
			
		}
		else if(ConnectMethod == 3) {				//ͨѶ��ʽ3=7098ͨѶת��
			
		}
		return 0;									//���ӳɹ�
	}
	
	/* �ر����Ӷ˿ڣ����ԶϿ����ӿ��ư壩
	 * ����ֵ= 0��ִ�гɹ�
	 * ����ֵ=-1������Ĵ��ں� */
	public int SetUnconnected() {
		if(ConnectMethod == 0) {					//ͨѶ��ʽ0=����ͨѶ
			if(SerialPortOpenedFlag) {
				SerialTool.closePort(serialPort);		//�رմ���
				SerialPortOpenedFlag = false;
			}
		}
		else if(ConnectMethod == 1) {				//ͨѶ��ʽ1=TCP/IPͨѶ
			
		}
		else if(ConnectMethod == 2) {				//ͨѶ��ʽ2=7182/7183ͨѶת��
			
		}
		else if(ConnectMethod == 3) {				//ͨѶ��ʽ3=7098ͨѶת��
			
		}
		return 0;									//���ӳɹ�
	}
	
	/* */
	int SendData(int fc1, int fc2, int waitT) {
		if(!ConnectedFlag) return 0;				//���ư�δ����
		getSendDataHead(fc1, fc2);
		getSendDataEnd();
		return 1;									//���ͳɹ�
	}
	
	/**
	 * ���ڲ�����ʽ����һ�����ڼ�����
	 * @author xuxingxing
	 *
	 */
	private class SerialListener implements SerialPortEventListener {
	    public void serialEvent(SerialPortEvent serialPortEvent) {//�����ص��Ĵ����¼�
	        switch (serialPortEvent.getEventType()) {
	            case SerialPortEvent.BI: // 10 ͨѶ�ж�
	            	JOptionPane.showMessageDialog(null, "�봮���豸ͨѶ�ж�", "����", JOptionPane.INFORMATION_MESSAGE);
	            	break;
	            case SerialPortEvent.OE: // 7 ��λ�����������
	            case SerialPortEvent.FE: // 9 ֡����
	            case SerialPortEvent.PE: // 8 ��żУ�����
	            case SerialPortEvent.CD: // 6 �ز����
	            case SerialPortEvent.CTS: // 3 �������������
	            case SerialPortEvent.DSR: // 4 ����������׼������
	            case SerialPortEvent.RI: // 5 ����ָʾ
	            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
	            	break;
	            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ�������
	            	//System.out.println("found data");
					byte[] data = null;
					try {
						if (serialPort == null) {
							JOptionPane.showMessageDialog(null, "���ڶ���Ϊ�գ�����ʧ�ܣ�", "����", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							data = SerialTool.readFromPort(serialPort);	//��ȡ���ݣ������ֽ�����
							//System.out.println(new String(data));
							
							//�Զ����������
							if (data == null || data.length < 1) {	//��������Ƿ��ȡ��ȷ	
								JOptionPane.showMessageDialog(null, "��ȡ���ݹ�����δ��ȡ����Ч���ݣ������豸�����", "����", JOptionPane.INFORMATION_MESSAGE);
								System.exit(0);
							}
							else {
								for(int i = 0; i < data.length; i++) {
									RecvDataBuf[RecvAddIndex++] = (int)data[i];
								}
								//receiveTextArea.append(getReceiveData(data));
								//receiveCountField.setText(Integer.toString(Integer.parseInt(receiveCountField.getText()) + data.length));
							}
						}						
					} catch (ReadDataFromSerialPortFailure | SerialPortInputStreamCloseFailure e) {
						JOptionPane.showMessageDialog(null, e, "����", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);	//������ȡ����ʱ��ʾ������Ϣ���˳�ϵͳ
					}	
					break;
	        }
	    }
	}
}

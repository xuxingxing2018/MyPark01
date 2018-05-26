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
	private int [] RecvDataBuf = new int [65536];	//接收数据缓冲区
	private int [] SendDataBuf = new int [65536];	//发送数据缓冲区
	private int RecvAddIndex;						//数据增加指针
	private int RecvDoneIndex;						//数据处理指针
	private int SendAddIndex;						//数据增加指针
	private int SendDoneIndex;						//数据处理指针
	private String BoardIpAddress;					//控制板的IP地址
	private int RecvFc1;							//接收协议的功能码1
	private int RecvFc2;							//接收协议的功能码2
	private int RecvCrc1;							//接收协议的校验码1
	private int RecvCrc2;							//接收协议的校验码2
	private int RecvCrc;							//接收协议的校验码
	private static int RecvHead = 3;				//接收协议的协议头
	private static int RecvEnd = 4;					//接收协议的协议尾
	private static int RecvEtx = 13;				//接收协议的数据结束符
	private int SendFc1;							//发送协议的功能码1
	private int SendFc2;							//发送协议的功能码2
	private int SendCrc;							//发送协议的校验码
	private static int SendHead = 1;				//发送协议的协议头
	private static int SendEnd = 2;					//发送协议的协议尾
	private static int SendEtx = 13;				//发送协议的数据结束符
	private int WaitingTime;						//发送数据后等待应答的时间，当其为0时，无需等待应答
	private boolean ConnectedFlag;					//控制板已连接标志
	private int ConnectMethod;						//连接方式
	private SerialPort serialPort = null;			//保存串口对象
	private boolean SerialPortOpenedFlag;			//串口已打开标志位
	
	public Cp200n() {								//构造函数
		RecvAddIndex = 0;							//清空指针
		RecvDoneIndex = 0;							//清空指针
		SendAddIndex = 0;							//清空指针
		SendDoneIndex = 0;							//清空指针
		SerialPortOpenedFlag = false;				//
	}
	/* 填入发送数据头 */
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
	/* 填入发送数据尾 */
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
	
	/* 设置通讯方式（连接方法）。具体方法有：串口通讯、TCP/IP、7098SY通讯转发、7182SY通讯转发 */
	public void SetConnectMethod(int method) {
		if(ConnectMethod == 0) {					//通讯方式0=串口通讯
			if(SerialPortOpenedFlag) {
				SerialTool.closePort(serialPort);
				SerialPortOpenedFlag = false;
			}
		}
		else if(ConnectMethod == 1) {				//通讯方式1=TCP/IP通讯
			
		}
		else if(ConnectMethod == 2) {				//通讯方式2=7182/7183通讯转发
			
		}
		else if(ConnectMethod == 3) {				//通讯方式3=7098通讯转发
			
		}
		ConnectMethod = method;
	}
	
	/* 打开连接端口（尝试连接控制板），传入参数s为IP地址或串口号
	 * 返回值= 0，执行成功
	 * 返回值=-1，错误的串口号
	 * 返回值=-2，错误的串口波特率
	 * 返回值=-3，填写的字符不是一个串口号/不存在的串口号/该串口已被占用/该串口已被监听 
	 * 返回值=-4，串口重复打开 */
	public int SetConnected(String s) {
		if(ConnectMethod == 0) {					//通讯方式0=串口通讯
			if(SerialPortOpenedFlag) return -4;
			String commName = s;//获取串口名称			
			String bpsStr = "115200";//获取波特率
			if (commName == null || commName.equals("")) {//检查串口名称是否获取正确
				//JOptionPane.showMessageDialog(null, "没有搜索到有效串口！", "错误", JOptionPane.INFORMATION_MESSAGE);
				return -1;
			}
			if (bpsStr == null || bpsStr.equals("")) {//检查波特率是否获取正确
				//JOptionPane.showMessageDialog(null, "波特率获取错误！", "错误", JOptionPane.INFORMATION_MESSAGE);
				return -2;
			}
			int bps = Integer.parseInt(bpsStr);//串口名、波特率均获取正确时
			try {
				serialPort = SerialTool.openPort(commName, bps);//获取指定端口名及波特率的串口对象
				SerialTool.addListener(serialPort, new SerialListener());//在该串口对象上添加监听器
				SerialPortOpenedFlag = true;
				//监听成功进行提示
				//JOptionPane.showMessageDialog(null, "监听成功，稍后将显示监测数据！", "提示", JOptionPane.INFORMATION_MESSAGE);
				//串口已打开
			
			} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse | TooManyListeners e1) {
				//发生错误时使用一个Dialog提示具体的错误信息
				//JOptionPane.showMessageDialog(null, e1, "错误", JOptionPane.INFORMATION_MESSAGE);
				return -3;
			}
		}
		else if(ConnectMethod == 1) {				//通讯方式1=TCP/IP通讯
			
		}
		else if(ConnectMethod == 2) {				//通讯方式2=7182/7183通讯转发
			
		}
		else if(ConnectMethod == 3) {				//通讯方式3=7098通讯转发
			
		}
		return 0;									//连接成功
	}
	
	/* 关闭连接端口（尝试断开连接控制板）
	 * 返回值= 0，执行成功
	 * 返回值=-1，错误的串口号 */
	public int SetUnconnected() {
		if(ConnectMethod == 0) {					//通讯方式0=串口通讯
			if(SerialPortOpenedFlag) {
				SerialTool.closePort(serialPort);		//关闭串口
				SerialPortOpenedFlag = false;
			}
		}
		else if(ConnectMethod == 1) {				//通讯方式1=TCP/IP通讯
			
		}
		else if(ConnectMethod == 2) {				//通讯方式2=7182/7183通讯转发
			
		}
		else if(ConnectMethod == 3) {				//通讯方式3=7098通讯转发
			
		}
		return 0;									//连接成功
	}
	
	/* */
	int SendData(int fc1, int fc2, int waitT) {
		if(!ConnectedFlag) return 0;				//控制板未连接
		getSendDataHead(fc1, fc2);
		getSendDataEnd();
		return 1;									//发送成功
	}
	
	/**
	 * 以内部类形式创建一个串口监听类
	 * @author xuxingxing
	 *
	 */
	private class SerialListener implements SerialPortEventListener {
	    public void serialEvent(SerialPortEvent serialPortEvent) {//处理监控到的串口事件
	        switch (serialPortEvent.getEventType()) {
	            case SerialPortEvent.BI: // 10 通讯中断
	            	JOptionPane.showMessageDialog(null, "与串口设备通讯中断", "错误", JOptionPane.INFORMATION_MESSAGE);
	            	break;
	            case SerialPortEvent.OE: // 7 溢位（溢出）错误
	            case SerialPortEvent.FE: // 9 帧错误
	            case SerialPortEvent.PE: // 8 奇偶校验错误
	            case SerialPortEvent.CD: // 6 载波检测
	            case SerialPortEvent.CTS: // 3 清除待发送数据
	            case SerialPortEvent.DSR: // 4 待发送数据准备好了
	            case SerialPortEvent.RI: // 5 振铃指示
	            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
	            	break;
	            case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
	            	//System.out.println("found data");
					byte[] data = null;
					try {
						if (serialPort == null) {
							JOptionPane.showMessageDialog(null, "串口对象为空！监听失败！", "错误", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							data = SerialTool.readFromPort(serialPort);	//读取数据，存入字节数组
							//System.out.println(new String(data));
							
							//自定义解析过程
							if (data == null || data.length < 1) {	//检查数据是否读取正确	
								JOptionPane.showMessageDialog(null, "读取数据过程中未获取到有效数据！请检查设备或程序！", "错误", JOptionPane.INFORMATION_MESSAGE);
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
						JOptionPane.showMessageDialog(null, e, "错误", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);	//发生读取错误时显示错误信息后退出系统
					}	
					break;
	        }
	    }
	}
}

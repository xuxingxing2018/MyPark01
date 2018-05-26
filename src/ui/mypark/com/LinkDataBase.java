package ui.mypark.com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LinkDataBase extends JPanel{
	private JTextField jf1 = new JTextField(10);
	private JTextField jf2 = new JTextField(10);
	private JTextField jf3 = new JTextField(10);
	private JTextField jf4 = new JTextField(10);
	private JButton ceshi = new JButton("�������ݿ�����");
	private String content;
	private String ms1;
	private String ms2;
	private String ms3;
	private String ms4;
	DataBase db = new DataBase();
	public LinkDataBase(JFrame jf) {
		Log_Exception le=new Log_Exception();
		try { //���Զ�ȡ�����ļ�
			ms1 = le.readDat_from_txt(mainJFrameUi.DBConfigPath, 3); 
			ms2 = le.readDat_from_txt(mainJFrameUi.DBConfigPath, 6);
			ms3 = le.readDat_from_txt(mainJFrameUi.DBConfigPath, 9);
			ms4 = le.readDat_from_txt(mainJFrameUi.DBConfigPath, 12);
			if(ms1 == null || ms2 == null || ms3 == null || ms4 == null) {
				ms1 = "127.0.0.1";
				ms2 = "MyPark01";
				ms3 = "root";
				ms4 = "xxxelder123!!!4";
				jf1.setText(ms1);
				jf2.setText(ms2);
				jf3.setText(ms3);
				jf4.setText(ms4);
				content = "��Ҫ�����ļ��������޸�\r\n\r\n������IP��ַ��\r\n" + jf1.getText() + 
						"\r\n\r\n���ݿ����ƣ�\r\n" + jf2.getText() + 
						"\r\n\r\n���ݿ��¼����\r\n" + jf3.getText() + 
						"\r\n\r\n���ݿ����룺\r\n" + jf4.getText(); 
				le.writeDat_to_txt(mainJFrameUi.DBConfigPath, content);
			} else {
				jf1.setText(ms1);
				jf2.setText(ms2);
				jf3.setText(ms3);
				jf4.setText(ms4);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setLayout(new BorderLayout());
		JPanel jp = new JPanel(new GridLayout(4, 2, 5, 5));
		jp.add(new JLabel("  ������IP��ַ����������"));
		jp.add(jf1);
		jp.add(new JLabel("  ���ݿ����ƣ�"));
		jp.add(jf2);
		jp.add(new JLabel("  ���ݿ��½����"));
		jp.add(jf3);
		jp.add(new JLabel("  ���ݿ����룺"));
		jp.add(jf4);
		add(jp, BorderLayout.CENTER);
		add(ceshi, BorderLayout.SOUTH);
		ceshi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("����˰�ť��");
                // JOptionPane.showConfirmDialog(null, "��������3", "���Ǳ���3", JOptionPane.YES_NO_OPTION);
        		db.testDataBase(jf2.getText());
        		List<List<Object>> x = db.getData(jf2.getText(), "select Name from SystemUser");
        		System.out.println("name = " + x);
        		if(mainJFrameUi.DataBaseLinkFlag) {// ���ݿ�����������־λ
        			System.out.println("�����������ݿ�ɹ�������");
        			// System.exit(0);
        			jf.dispose();// �ر��������ݿ���Ӵ���
        		} else {
        			System.out.println("�����������ݿ�ʧ�ܣ�����");
        		}
            }           
        });
	}
	public Dimension getPreferredSize() {
		return new Dimension(390, 200);
	}

}

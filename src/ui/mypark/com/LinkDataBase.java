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
	private JButton ceshi = new JButton("测试数据库连接");
	private String content;
	private String ms1;
	private String ms2;
	private String ms3;
	private String ms4;
	DataBase db = new DataBase();
	public LinkDataBase(JFrame jf) {
		Log_Exception le=new Log_Exception();
		try { //尝试读取配置文件
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
				content = "重要配置文件，请勿修改\r\n\r\n服务器IP地址：\r\n" + jf1.getText() + 
						"\r\n\r\n数据库名称：\r\n" + jf2.getText() + 
						"\r\n\r\n数据库登录名：\r\n" + jf3.getText() + 
						"\r\n\r\n数据库密码：\r\n" + jf4.getText(); 
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
		jp.add(new JLabel("  服务器IP地址或计算机名："));
		jp.add(jf1);
		jp.add(new JLabel("  数据库名称："));
		jp.add(jf2);
		jp.add(new JLabel("  数据库登陆名："));
		jp.add(jf3);
		jp.add(new JLabel("  数据库密码："));
		jp.add(jf4);
		add(jp, BorderLayout.CENTER);
		add(ceshi, BorderLayout.SOUTH);
		ceshi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.print("点击了按钮呢");
                // JOptionPane.showConfirmDialog(null, "这是内容3", "这是标题3", JOptionPane.YES_NO_OPTION);
        		db.testDataBase(jf2.getText());
        		List<List<Object>> x = db.getData(jf2.getText(), "select Name from SystemUser");
        		System.out.println("name = " + x);
        		if(mainJFrameUi.DataBaseLinkFlag) {// 数据库连接正常标志位
        			System.out.println("测试连接数据库成功！！！");
        			// System.exit(0);
        			jf.dispose();// 关闭连接数据库的子窗口
        		} else {
        			System.out.println("测试连接数据库失败！！！");
        		}
            }           
        });
	}
	public Dimension getPreferredSize() {
		return new Dimension(390, 200);
	}

}

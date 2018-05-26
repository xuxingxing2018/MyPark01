package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/** 主菜单转至登陆系统事件监听器 */
public class MenuToLoginListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
    	Login login = new Login();
    	JFrame jf = new JFrame();
    	jf.add(login);
    	jf.pack();
    	jf.setTitle("登陆系统");
    	jf.setLocationRelativeTo(null);				//设置窗口居中
    	jf.setVisible(true);
    }
}

package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/** ���˵�ת����½ϵͳ�¼������� */
public class MenuToLoginListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
    	Login login = new Login();
    	JFrame jf = new JFrame();
    	jf.add(login);
    	jf.pack();
    	jf.setTitle("��½ϵͳ");
    	jf.setLocationRelativeTo(null);				//���ô��ھ���
    	jf.setVisible(true);
    }
}

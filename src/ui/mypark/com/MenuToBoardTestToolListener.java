package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/** ���˵�ת����幤���¼������� */
public class MenuToBoardTestToolListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
    	BoardTestTool btt = new BoardTestTool();
    	JFrame jf = new JFrame();
    	jf.add(btt);
    	jf.pack();
    	jf.setTitle("��幤��");
    	jf.setLocationRelativeTo(null);				//���ô��ھ���
    	jf.setVisible(true);
    }
}

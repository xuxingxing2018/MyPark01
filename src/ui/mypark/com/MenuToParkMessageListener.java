package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuToParkMessageListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		ParkMessage parkmessage = new ParkMessage();
    	JFrame jf = new JFrame();
    	jf.add(parkmessage);
    	jf.pack();
    	jf.setTitle("ͣ������Ϣ");
    	jf.setLocationRelativeTo(null);				//���ô��ھ���
    	jf.setVisible(true);
	}
}

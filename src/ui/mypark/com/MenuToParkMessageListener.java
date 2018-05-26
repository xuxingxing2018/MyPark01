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
    	jf.setTitle("停车场信息");
    	jf.setLocationRelativeTo(null);				//设置窗口居中
    	jf.setVisible(true);
	}
}

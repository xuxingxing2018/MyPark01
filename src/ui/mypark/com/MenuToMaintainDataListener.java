package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuToMaintainDataListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		MaintainData maintainData = new MaintainData();
    	JFrame jf = new JFrame();
    	jf.add(maintainData);
    	jf.pack();
    	jf.setTitle("ά������");
    	jf.setLocationRelativeTo(null);				//���ô��ھ���
    	jf.setVisible(true);
    }
}

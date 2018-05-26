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
    	jf.setTitle("维护数据");
    	jf.setLocationRelativeTo(null);				//设置窗口居中
    	jf.setVisible(true);
    }
}

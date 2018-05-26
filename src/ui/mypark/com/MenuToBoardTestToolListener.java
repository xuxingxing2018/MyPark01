package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
/** 主菜单转至测板工具事件监听器 */
public class MenuToBoardTestToolListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
    	BoardTestTool btt = new BoardTestTool();
    	JFrame jf = new JFrame();
    	jf.add(btt);
    	jf.pack();
    	jf.setTitle("测板工具");
    	jf.setLocationRelativeTo(null);				//设置窗口居中
    	jf.setVisible(true);
    }
}

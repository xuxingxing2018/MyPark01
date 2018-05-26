package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MenuToRolePermissionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		RolePermission rolePermission = new RolePermission();
    	JFrame jf = new JFrame();
    	jf.add(rolePermission);
    	jf.pack();
    	jf.setTitle("角色权限");
    	jf.setLocationRelativeTo(null);				//设置窗口居中
    	jf.setVisible(true);
    }
}

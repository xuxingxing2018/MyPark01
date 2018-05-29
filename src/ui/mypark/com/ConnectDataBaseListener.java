package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

public class ConnectDataBaseListener  implements ActionListener {
	private JFrame databaseJFrame = new JFrame();
	private mainJFrameUi a = null;
	public ConnectDataBaseListener(mainJFrameUi mainJFrame) {
		this.a = mainJFrame;
	}
	public void actionPerformed(ActionEvent e) {
		//List<List<Object>> x = DataBase.getData("MyPark01", "select FILE_NAME from FILES");
		LinkDataBase link = new LinkDataBase(databaseJFrame, a);
    	
		databaseJFrame.add(link);
		databaseJFrame.pack();
		databaseJFrame.setTitle("连接数据库");
		databaseJFrame.setLocationRelativeTo(null);				//设置窗口居中
		databaseJFrame.setVisible(true);
    }
}

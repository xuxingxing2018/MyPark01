package ui.mypark.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

public class ConnectDataBaseListener  implements ActionListener {
	private JFrame databaseJFrame = new JFrame();
	public void actionPerformed(ActionEvent e) {
		//List<List<Object>> x = DataBase.getData("MyPark01", "select FILE_NAME from FILES");
		LinkDataBase link = new LinkDataBase(databaseJFrame);
    	
		databaseJFrame.add(link);
		databaseJFrame.pack();
		databaseJFrame.setTitle("�������ݿ�");
		databaseJFrame.setLocationRelativeTo(null);				//���ô��ھ���
		databaseJFrame.setVisible(true);
    }
}

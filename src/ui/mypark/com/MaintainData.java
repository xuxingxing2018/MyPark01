package ui.mypark.com;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* 维护数据
 * */
public class MaintainData extends JPanel{
	JLabel lab1 = new JLabel("注：删除制定日期前的抓拍图像，但未出场的在场车辆图像不会被删除");
	JButton jb1 = new JButton("删除抓拍图像");
	JButton jb2 = new JButton("删除出入记录");
	JButton jb3 = new JButton("备份所有数据");
	JButton jb4 = new JButton("恢复所有数据");
	JButton jb5 = new JButton("清除已注销的车辆档案");
	JButton jb6 = new JButton("清除被禁用的车辆档案");
	JButton jb7 = new JButton("清除已注销的卡片档案");
	JButton jb8 = new JButton("清除被禁用的卡片档案");
	public MaintainData() {
		add(lab1);
		add(jb1);
		add(jb2);
		add(jb3);
		add(jb4);
		add(jb5);
		add(jb6);
		add(jb7);
		add(jb8);
		jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
	}
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}

}

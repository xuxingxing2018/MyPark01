package ui.mypark.com;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardTestTool extends JPanel {
	JLabel lab1 = new JLabel("ͨѶ��ʽ");
	public BoardTestTool() {
		add(lab1);
	}
	public Dimension getPreferredSize() {
		return new Dimension(800, 600);
	}
}

package ui.mypark.com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Login extends JPanel {
	private JLabel lab_state = new JLabel("��ǰ���û���½��Ӧ��0Ԫ��ʵ��0Ԫ");
	private JLabel lab1 = new JLabel("    �û�����");
	private JComboBox jcb1 = new JComboBox();
	private JLabel lab2 = new JLabel("    ���룺");
	private JTextField jf1 = new JTextField(10);
	private JButton jb1 = new JButton("��½");
	private JButton jb2 = new JButton("ȡ��");
	//private Border lineBorder = new LineBorder(Color.BLACK, 2);	//�߽߱�
	public Login() {
		//lab_state��setBorder(lineBorder);
		JPanel jp1 = new JPanel(new GridLayout(3, 2, 10, 20));
		JPanel jp2 = new JPanel(new FlowLayout());
		setLayout(new BorderLayout(10, 20));
		jp1.add(lab1);
		jp1.add(jcb1);
		jp1.add(lab2);
		jp1.add(jf1);
		jp1.add(jb1);
		jp1.add(jb2);
		jp2.add(lab_state);
		jp2.setBorder(new TitledBorder("��ǰ��½״̬��Ӧ�ա�ʵ��"));
		add(jp1, BorderLayout.CENTER);
		add(jp2, BorderLayout.NORTH);
	}
	public Dimension getPreferredSize() {
		return new Dimension(350, 200);
	}
}

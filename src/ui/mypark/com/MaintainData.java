package ui.mypark.com;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/* ά������
 * */
public class MaintainData extends JPanel{
	JLabel lab1 = new JLabel("ע��ɾ���ƶ�����ǰ��ץ��ͼ�񣬵�δ�������ڳ�����ͼ�񲻻ᱻɾ��");
	JButton jb1 = new JButton("ɾ��ץ��ͼ��");
	JButton jb2 = new JButton("ɾ�������¼");
	JButton jb3 = new JButton("������������");
	JButton jb4 = new JButton("�ָ���������");
	JButton jb5 = new JButton("�����ע���ĳ�������");
	JButton jb6 = new JButton("��������õĳ�������");
	JButton jb7 = new JButton("�����ע���Ŀ�Ƭ����");
	JButton jb8 = new JButton("��������õĿ�Ƭ����");
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

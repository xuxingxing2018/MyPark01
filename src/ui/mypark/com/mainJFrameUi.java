package ui.mypark.com;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class mainJFrameUi extends JFrame{
	public static boolean DataBaseLinkFlag = false;
	public static String DBConfigPath = "D:/ParkData/Config/DBConfig.txt";
	// ����Ϊ1���Ӳ˵���1�鰴ť
	private JMenuItem sbar11 = new JMenuItem("��¼(A)");
	private JMenuItem sbar12 = new JMenuItem("��ɫȨ��(B)");
	private JMenuItem sbar13 = new JMenuItem("ϵͳ��־(C)");
	private JMenuItem sbar14 = new JMenuItem("��幤��(D)");
	private JMenuItem sbar15 = new JMenuItem("����ע��(E)");
	private JButton jbutton11 = new JButton("��¼");
	private JButton jbutton12 = new JButton("��ɫȨ��");
	private JButton jbutton13 = new JButton("ϵͳ��־");
	private JButton jbutton14 = new JButton("��幤��");
	private JButton jbutton15 = new JButton("����ע��");
	// ����Ϊ2���Ӳ˵���2�鰴ť
	private JMenuItem sbar21 = new JMenuItem("ͣ������Ϣ(A)");
	private JMenuItem sbar22 = new JMenuItem("�շѱ�׼(B)");
	private JMenuItem sbar23 = new JMenuItem("��������(C)");
	private JMenuItem sbar24 = new JMenuItem("��Լ�̻�����(D)");
	private JMenuItem sbar25 = new JMenuItem("���̿���ϸ�ڲ���(E)");
	private JButton jbutton21 = new JButton("ͣ������Ϣ");
	private JButton jbutton22 = new JButton("�շѱ�׼");
	private JButton jbutton23 = new JButton("��������");
	private JButton jbutton24 = new JButton("��Լ�̻�����");
	private JButton jbutton25 = new JButton("���̿���ϸ�ڲ���");
	// ����Ϊ3���Ӳ˵���3�鰴ť
	private JMenuItem sbar31 = new JMenuItem("ʵʱ���(A)");
	private JMenuItem sbar32 = new JMenuItem("�ڳ�������ѯ(B)");
	private JButton jbutton31 = new JButton("ʵʱ���");
	private JButton jbutton32 = new JButton("�ڳ�������ѯ");
	// ����Ϊ4���Ӳ˵���4�鰴ť
	private JMenuItem sbar41 = new JMenuItem("����������¼(A)");
	private JMenuItem sbar42 = new JMenuItem("�쳣������¼(B)");
	private JMenuItem sbar43 = new JMenuItem("����ʹ�ü�¼(C)");
	private JMenuItem sbar44 = new JMenuItem("�շѽ��Ӽ�¼(D)");
	private JMenuItem sbar45 = new JMenuItem("ֵ����ˮ��¼(E)");
	private JMenuItem sbar46 = new JMenuItem("��ѯ�շѼ�¼(F)");
	private JButton jbutton41 = new JButton("����������¼");
	private JButton jbutton42 = new JButton("�쳣������¼");
	private JButton jbutton43 = new JButton("����ʹ�ü�¼");
	private JButton jbutton44 = new JButton("�շѽ��Ӽ�¼");
	private JButton jbutton45 = new JButton("ֵ����ˮ��¼");
	private JButton jbutton46 = new JButton("��ѯ�շѼ�¼");
	// ����Ϊ5���Ӳ˵���5�鰴ť
	private JMenuItem sbar51 = new JMenuItem("�շ�Ա�շ�ͳ�Ʊ���(A)");
	private JMenuItem sbar52 = new JMenuItem("����Ա�տ�ͳ�Ʊ���(B)");
	private JMenuItem sbar53 = new JMenuItem("ͣ�����տ�ͳ�Ʊ���(C)");
	private JMenuItem sbar54 = new JMenuItem("�볡����ͳ�Ʊ���(D)");
	private JMenuItem sbar55 = new JMenuItem("��������ͳ�Ʊ���(E)");
	private JMenuItem sbar56 = new JMenuItem("�Ż��̻��շ�ͳ�Ʊ���(F)");
	private JButton jbutton51 = new JButton("�շ�Ա�շ�ͳ�Ʊ���");
	private JButton jbutton52 = new JButton("����Ա�տ�ͳ�Ʊ���");
	private JButton jbutton53 = new JButton("ͣ�����տ�ͳ�Ʊ���");
	private JButton jbutton54 = new JButton("�볡����ͳ�Ʊ���");
	private JButton jbutton55 = new JButton("��������ͳ�Ʊ���");
	private JButton jbutton56 = new JButton("�Ż��̻��շ�ͳ�Ʊ���");
	// ����Ϊ6���Ӳ˵���6�鰴ť
	private JMenuItem sbar61 = new JMenuItem("�汾������Ϣ(A)");
	private JMenuItem sbar62 = new JMenuItem("���˵����(B)");
	private JMenuItem sbar63 = new JMenuItem("��Ƶ�̳�(C)");
	private JButton jbutton61 = new JButton("�汾������Ϣ");
	private JButton jbutton62 = new JButton("���˵����");
	private JButton jbutton63 = new JButton("��Ƶ�̳�");
	// ����Ϊ7�鰴ť
	private JButton jbutton71 = new JButton("�������ݿ�");
	private JButton jbutton72 = new JButton("ά�����ݿ�");
	
	public mainJFrameUi() {
		JMenuBar bar = new JMenuBar();
		JMenu mbar1 = new JMenu("ϵͳ(1)");
		mbar1.setMnemonic('1');
		sbar11.setMnemonic('A');
		sbar12.setMnemonic('B');
		sbar13.setMnemonic('C');
		sbar14.setMnemonic('D');
		sbar15.setMnemonic('E');
		mbar1.add(sbar11);
		mbar1.add(sbar12);
		mbar1.add(sbar13);
		mbar1.add(sbar14);
		mbar1.add(sbar15);
		JMenu mbar2 = new JMenu("����ͣ����(2)");
		mbar2.setMnemonic('2');
		sbar21.setMnemonic('A');
		sbar22.setMnemonic('B');
		sbar23.setMnemonic('C');
		sbar24.setMnemonic('D');
		sbar25.setMnemonic('E');
		mbar2.add(sbar21);
		mbar2.add(sbar22);
		mbar2.add(sbar23);
		mbar2.add(sbar24);
		mbar2.add(sbar25);
		JMenu mbar3 = new JMenu("����ʵʱ���(3)");
		mbar3.setMnemonic('3');
		sbar31.setMnemonic('A');
		sbar32.setMnemonic('B');
		mbar3.add(sbar31);
		mbar3.add(sbar32);
		JMenu mbar4 = new JMenu("��¼��ѯ(4)");
		mbar4.setMnemonic('4');
		sbar41.setMnemonic('A');
		sbar42.setMnemonic('B');
		sbar43.setMnemonic('C');
		sbar44.setMnemonic('D');
		sbar45.setMnemonic('E');
		sbar46.setMnemonic('F');
		mbar4.add(sbar41);
		mbar4.add(sbar42);
		mbar4.add(sbar43);
		mbar4.add(sbar44);
		mbar4.add(sbar45);
		mbar4.add(sbar46);
		JMenu mbar5 = new JMenu("ͳ�Ʊ���(5)");
		mbar5.setMnemonic('5');
		sbar51.setMnemonic('A');
		sbar52.setMnemonic('B');
		sbar53.setMnemonic('C');
		sbar54.setMnemonic('D');
		sbar55.setMnemonic('E');
		sbar56.setMnemonic('F');
		mbar5.add(sbar51);
		mbar5.add(sbar52);
		mbar5.add(sbar53);
		mbar5.add(sbar54);
		mbar5.add(sbar55);
		mbar5.add(sbar56);
		JMenu mbar6 = new JMenu("����(6)");
		mbar6.setMnemonic('6');
		sbar61.setMnemonic('A');
		sbar62.setMnemonic('B');
		sbar63.setMnemonic('C');
		mbar6.add(sbar61);
		mbar6.add(sbar62);
		mbar6.add(sbar63);
		bar.add(mbar1);
		bar.add(mbar2);
		bar.add(mbar3);
		bar.add(mbar4);
		bar.add(mbar5);
		bar.add(mbar6);
		setJMenuBar(bar);
		setLayout(new GridLayout(8, 1, 10, 10));
		JTextArea jta = new JTextArea("ϵͳ��Ϣ��");
		JScrollPane scrollPane = new JScrollPane(jta);
		add(scrollPane);
		JPanel jp1 = new JPanel();
		jp1.setBorder(new TitledBorder("ϵͳ"));
		jp1.add(jbutton11);
		jp1.add(jbutton12);
		jp1.add(jbutton13);
		jp1.add(jbutton14);
		jp1.add(jbutton15);
		add(jp1);
		JPanel jp2 = new JPanel();
		jp2.setBorder(new TitledBorder("����ͣ����"));
		jp2.add(jbutton21);
		jp2.add(jbutton22);
		jp2.add(jbutton23);
		jp2.add(jbutton24);
		jp2.add(jbutton25);
		add(jp2);
		JPanel jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("����ʵʱ���"));
		jp3.add(jbutton31);
		jp3.add(jbutton32);
		add(jp3);
		JPanel jp4 = new JPanel();
		jp4.setBorder(new TitledBorder("��¼��ѯ"));
		jp4.add(jbutton41);
		jp4.add(jbutton42);
		jp4.add(jbutton43);
		jp4.add(jbutton44);
		jp4.add(jbutton45);
		jp4.add(jbutton46);
		add(jp4);
		JPanel jp5 = new JPanel();
		jp5.setBorder(new TitledBorder("ͳ�Ʊ���"));
		jp5.add(jbutton51);
		jp5.add(jbutton52);
		jp5.add(jbutton53);
		jp5.add(jbutton54);
		jp5.add(jbutton55);
		jp5.add(jbutton56);
		add(jp5);
		JPanel jp6 = new JPanel();
		jp6.setBorder(new TitledBorder("����"));
		jp6.add(jbutton61);
		jp6.add(jbutton62);
		jp6.add(jbutton63);
		add(jp6);
		JPanel jp7 = new JPanel();
		jp7.setBorder(new TitledBorder("���ݿ�"));
		jp7.add(jbutton71);
		jp7.add(jbutton72);
		add(jp7);
		setVisible(true);
		//mainJFrame.addWindowListener(ActionEvent e);
		//sbar11.addActionListener(this);
		setButtonView();
	}

	public void setButtonView() {
		if(DataBaseLinkFlag) {
			jbutton11.setEnabled(true);
			jbutton12.setEnabled(true);
			jbutton13.setEnabled(true);
			jbutton14.setEnabled(true);
			jbutton21.setEnabled(true);
			jbutton22.setEnabled(true);
			jbutton23.setEnabled(true);
			jbutton24.setEnabled(true);
			jbutton25.setEnabled(true);
			jbutton31.setEnabled(true);
			jbutton32.setEnabled(true);
			jbutton41.setEnabled(true);
			jbutton42.setEnabled(true);
			jbutton43.setEnabled(true);
			jbutton44.setEnabled(true);
			jbutton45.setEnabled(true);
			jbutton46.setEnabled(true);
			jbutton51.setEnabled(true);
			jbutton52.setEnabled(true);
			jbutton53.setEnabled(true);
			jbutton54.setEnabled(true);
			jbutton55.setEnabled(true);
			jbutton56.setEnabled(true);
			jbutton72.setEnabled(true);
		} else {
			jbutton11.setEnabled(false);
			jbutton12.setEnabled(false);
			jbutton13.setEnabled(false);
			jbutton14.setEnabled(false);
			jbutton21.setEnabled(false);
			jbutton22.setEnabled(false);
			jbutton23.setEnabled(false);
			jbutton24.setEnabled(false);
			jbutton25.setEnabled(false);
			jbutton31.setEnabled(false);
			jbutton32.setEnabled(false);
			jbutton41.setEnabled(false);
			jbutton42.setEnabled(false);
			jbutton43.setEnabled(false);
			jbutton44.setEnabled(false);
			jbutton45.setEnabled(false);
			jbutton46.setEnabled(false);
			jbutton51.setEnabled(false);
			jbutton52.setEnabled(false);
			jbutton53.setEnabled(false);
			jbutton54.setEnabled(false);
			jbutton55.setEnabled(false);
			jbutton56.setEnabled(false);
			jbutton72.setEnabled(false);
		}
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		mainJFrameUi mainJFrame = new mainJFrameUi();
		mainJFrame.sbar11.addActionListener(new MenuToLoginListener());//����¼�������
		mainJFrame.jbutton11.addActionListener(new MenuToLoginListener());//����¼�������
		mainJFrame.sbar12.addActionListener(new MenuToRolePermissionListener());//����¼�������
		mainJFrame.jbutton12.addActionListener(new MenuToRolePermissionListener());//����¼�������
		mainJFrame.sbar14.addActionListener(new MenuToBoardTestToolListener());//����¼�������
		mainJFrame.jbutton14.addActionListener(new MenuToBoardTestToolListener());//����¼�������
		mainJFrame.sbar21.addActionListener(new MenuToParkMessageListener());//����¼�������
		mainJFrame.jbutton21.addActionListener(new MenuToParkMessageListener());//����¼�������
		
		mainJFrame.jbutton71.addActionListener(new ConnectDataBaseListener(mainJFrame));//����¼�������
		mainJFrame.jbutton72.addActionListener(new MenuToMaintainDataListener());//����¼�������
		mainJFrame.setTitle("����ͣ�����������MyPark  �汾��MP2018_0410   ������˾��xxxx���޹�˾  �绰��13691645742 ");
		mainJFrame.setSize(1366, 768);
		mainJFrame.setLocationRelativeTo(null);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}

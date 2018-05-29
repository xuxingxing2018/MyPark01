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
	// 下面为1组子菜单和1组按钮
	private JMenuItem sbar11 = new JMenuItem("登录(A)");
	private JMenuItem sbar12 = new JMenuItem("角色权限(B)");
	private JMenuItem sbar13 = new JMenuItem("系统日志(C)");
	private JMenuItem sbar14 = new JMenuItem("测板工具(D)");
	private JMenuItem sbar15 = new JMenuItem("正版注册(E)");
	private JButton jbutton11 = new JButton("登录");
	private JButton jbutton12 = new JButton("角色权限");
	private JButton jbutton13 = new JButton("系统日志");
	private JButton jbutton14 = new JButton("测板工具");
	private JButton jbutton15 = new JButton("正版注册");
	// 下面为2组子菜单和2组按钮
	private JMenuItem sbar21 = new JMenuItem("停车场信息(A)");
	private JMenuItem sbar22 = new JMenuItem("收费标准(B)");
	private JMenuItem sbar23 = new JMenuItem("车辆档案(C)");
	private JMenuItem sbar24 = new JMenuItem("特约商户档案(D)");
	private JMenuItem sbar25 = new JMenuItem("流程控制细节参数(E)");
	private JButton jbutton21 = new JButton("停车场信息");
	private JButton jbutton22 = new JButton("收费标准");
	private JButton jbutton23 = new JButton("车辆档案");
	private JButton jbutton24 = new JButton("特约商户档案");
	private JButton jbutton25 = new JButton("流程控制细节参数");
	// 下面为3组子菜单和3组按钮
	private JMenuItem sbar31 = new JMenuItem("实时监控(A)");
	private JMenuItem sbar32 = new JMenuItem("在场车辆查询(B)");
	private JButton jbutton31 = new JButton("实时监控");
	private JButton jbutton32 = new JButton("在场车辆查询");
	// 下面为4组子菜单和4组按钮
	private JMenuItem sbar41 = new JMenuItem("车辆进出记录(A)");
	private JMenuItem sbar42 = new JMenuItem("异常进出记录(B)");
	private JMenuItem sbar43 = new JMenuItem("车辆使用记录(C)");
	private JMenuItem sbar44 = new JMenuItem("收费交接记录(D)");
	private JMenuItem sbar45 = new JMenuItem("值班流水记录(E)");
	private JMenuItem sbar46 = new JMenuItem("查询收费记录(F)");
	private JButton jbutton41 = new JButton("车辆进出记录");
	private JButton jbutton42 = new JButton("异常进出记录");
	private JButton jbutton43 = new JButton("车辆使用记录");
	private JButton jbutton44 = new JButton("收费交接记录");
	private JButton jbutton45 = new JButton("值班流水记录");
	private JButton jbutton46 = new JButton("查询收费记录");
	// 下面为5组子菜单和5组按钮
	private JMenuItem sbar51 = new JMenuItem("收费员收费统计报表(A)");
	private JMenuItem sbar52 = new JMenuItem("管理员收款统计报表(B)");
	private JMenuItem sbar53 = new JMenuItem("停车场收款统计报表(C)");
	private JMenuItem sbar54 = new JMenuItem("入场流量统计报表(D)");
	private JMenuItem sbar55 = new JMenuItem("出场流量统计报表(E)");
	private JMenuItem sbar56 = new JMenuItem("优惠商户收费统计报表(F)");
	private JButton jbutton51 = new JButton("收费员收费统计报表");
	private JButton jbutton52 = new JButton("管理员收款统计报表");
	private JButton jbutton53 = new JButton("停车场收款统计报表");
	private JButton jbutton54 = new JButton("入场流量统计报表");
	private JButton jbutton55 = new JButton("出场流量统计报表");
	private JButton jbutton56 = new JButton("优惠商户收费统计报表");
	// 下面为6组子菜单和6组按钮
	private JMenuItem sbar61 = new JMenuItem("版本更新信息(A)");
	private JMenuItem sbar62 = new JMenuItem("软件说明书(B)");
	private JMenuItem sbar63 = new JMenuItem("视频教程(C)");
	private JButton jbutton61 = new JButton("版本更新信息");
	private JButton jbutton62 = new JButton("软件说明书");
	private JButton jbutton63 = new JButton("视频教程");
	// 下面为7组按钮
	private JButton jbutton71 = new JButton("连接数据库");
	private JButton jbutton72 = new JButton("维护数据库");
	
	public mainJFrameUi() {
		JMenuBar bar = new JMenuBar();
		JMenu mbar1 = new JMenu("系统(1)");
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
		JMenu mbar2 = new JMenu("设置停车场(2)");
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
		JMenu mbar3 = new JMenu("车道实时监控(3)");
		mbar3.setMnemonic('3');
		sbar31.setMnemonic('A');
		sbar32.setMnemonic('B');
		mbar3.add(sbar31);
		mbar3.add(sbar32);
		JMenu mbar4 = new JMenu("记录查询(4)");
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
		JMenu mbar5 = new JMenu("统计报表(5)");
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
		JMenu mbar6 = new JMenu("帮助(6)");
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
		JTextArea jta = new JTextArea("系统信息框");
		JScrollPane scrollPane = new JScrollPane(jta);
		add(scrollPane);
		JPanel jp1 = new JPanel();
		jp1.setBorder(new TitledBorder("系统"));
		jp1.add(jbutton11);
		jp1.add(jbutton12);
		jp1.add(jbutton13);
		jp1.add(jbutton14);
		jp1.add(jbutton15);
		add(jp1);
		JPanel jp2 = new JPanel();
		jp2.setBorder(new TitledBorder("设置停车场"));
		jp2.add(jbutton21);
		jp2.add(jbutton22);
		jp2.add(jbutton23);
		jp2.add(jbutton24);
		jp2.add(jbutton25);
		add(jp2);
		JPanel jp3 = new JPanel();
		jp3.setBorder(new TitledBorder("车道实时监控"));
		jp3.add(jbutton31);
		jp3.add(jbutton32);
		add(jp3);
		JPanel jp4 = new JPanel();
		jp4.setBorder(new TitledBorder("记录查询"));
		jp4.add(jbutton41);
		jp4.add(jbutton42);
		jp4.add(jbutton43);
		jp4.add(jbutton44);
		jp4.add(jbutton45);
		jp4.add(jbutton46);
		add(jp4);
		JPanel jp5 = new JPanel();
		jp5.setBorder(new TitledBorder("统计报表"));
		jp5.add(jbutton51);
		jp5.add(jbutton52);
		jp5.add(jbutton53);
		jp5.add(jbutton54);
		jp5.add(jbutton55);
		jp5.add(jbutton56);
		add(jp5);
		JPanel jp6 = new JPanel();
		jp6.setBorder(new TitledBorder("帮助"));
		jp6.add(jbutton61);
		jp6.add(jbutton62);
		jp6.add(jbutton63);
		add(jp6);
		JPanel jp7 = new JPanel();
		jp7.setBorder(new TitledBorder("数据库"));
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
		// TODO 自动生成的方法存根
		mainJFrameUi mainJFrame = new mainJFrameUi();
		mainJFrame.sbar11.addActionListener(new MenuToLoginListener());//添加事件监听器
		mainJFrame.jbutton11.addActionListener(new MenuToLoginListener());//添加事件监听器
		mainJFrame.sbar12.addActionListener(new MenuToRolePermissionListener());//添加事件监听器
		mainJFrame.jbutton12.addActionListener(new MenuToRolePermissionListener());//添加事件监听器
		mainJFrame.sbar14.addActionListener(new MenuToBoardTestToolListener());//添加事件监听器
		mainJFrame.jbutton14.addActionListener(new MenuToBoardTestToolListener());//添加事件监听器
		mainJFrame.sbar21.addActionListener(new MenuToParkMessageListener());//添加事件监听器
		mainJFrame.jbutton21.addActionListener(new MenuToParkMessageListener());//添加事件监听器
		
		mainJFrame.jbutton71.addActionListener(new ConnectDataBaseListener(mainJFrame));//添加事件监听器
		mainJFrame.jbutton72.addActionListener(new MenuToMaintainDataListener());//添加事件监听器
		mainJFrame.setTitle("智能停车场管理软件MyPark  版本：MP2018_0410   开发公司：xxxx有限公司  电话：13691645742 ");
		mainJFrame.setSize(1366, 768);
		mainJFrame.setLocationRelativeTo(null);
		mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
}

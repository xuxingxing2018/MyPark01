package ui.mypark.com;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableColumn;
/* 角色权限
 * JTable学习网址：
 * https://www.baidu.com/link?url=go89O0VhadWDI6EwlH9UwBLaegZk407Oc9bgk-FYlOdx5bdwZmfIsHb4-BGrfCqO6rhzT2EBNbSYovFgULtOXul9ph_QEbviye7WLEb5J8e&wd=&eqid=b8013e540005d80a000000065ad72dd2
 * */
public class RolePermission extends JPanel{
	private JButton zj_ = new JButton("增加");
	private JButton xg_ = new JButton("修改");
	private JButton sc_ = new JButton("删除");
	private JButton sh_ = new JButton("审核");
	private Object[][] obj1 = new Object[10][2];
	private String[] columnNames1 = { "用户名", "所属角色"};
	private JTable table1 = new JTable(obj1, columnNames1);
	
	private JButton zj = new JButton("增加");
	private JButton xg = new JButton("修改");
	private JButton sc = new JButton("删除");
	private JButton sh = new JButton("审核");
	//private JButton jb1 = new JButton("超级管理员（主司系统维护）");
	//private JButton jb2 = new JButton("收费员（主司现场收费）");
	//private JButton jb3 = new JButton("财会（主司报表统计）");
	//private JButton jb4 = new JButton("文员（主司用户登记）");
	private Object[][] obj2 = new Object[10][1];
	private String[] columnNames2 = { "角色"};
	private JTable table2 = new JTable(obj2, columnNames2);
	
	private Object[][] obj3 = new Object[100][9];
	private String[] columnNames3 = { "主模块", "模块编号", "模块名称", "浏览", "增加", "修改", "删除", "审核", "打印"};
	private JTable table3 = new JTable(obj3, columnNames3);
	
	public RolePermission() {
		JPanel jp1 = new JPanel(new BorderLayout(1, 5));
		jp1.setBorder(new TitledBorder("系统用户表"));
		JPanel jp1_1 = new JPanel(new GridLayout(4, 1, 5, 5));
		jp1_1.add(zj_);
		jp1_1.add(xg_);
		jp1_1.add(sc_);
		jp1_1.add(sh_);
		jp1.add(jp1_1, BorderLayout.NORTH);
		TableColumn column1 = null;
		for(int i = 0; i < table1.getColumnCount(); i++)  {  
            column1 = table1.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/  
            column1.setPreferredWidth(100);  
        }
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setPreferredScrollableViewportSize(new Dimension(200, 500));
		/*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  
        JScrollPane scroll1 = new JScrollPane(table1);  
        scroll1.setSize(100, 100);
        jp1.add(scroll1, BorderLayout.CENTER);
        
        JPanel jp2 = new JPanel(new BorderLayout(1, 5));
        jp2.setBorder(new TitledBorder("角色表"));
        JPanel jp2_1 = new JPanel(new GridLayout(4, 1, 5, 5));
        jp2_1.add(zj);
        jp2_1.add(xg);
        jp2_1.add(sc);
        jp2_1.add(sh);
        jp2.add(jp2_1, BorderLayout.NORTH);
		TableColumn column2 = null;
		for(int i = 0; i < table2.getColumnCount(); i++)  {  
            column2 = table2.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/  
            column2.setPreferredWidth(100);  
        }
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table2.setPreferredScrollableViewportSize(new Dimension(100, 500));
		/*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  
        JScrollPane scroll2 = new JScrollPane(table2);  
        scroll2.setSize(300, 200);
        jp2.add(scroll2, BorderLayout.CENTER);
        
        
		TableColumn column3 = null;
		for(int i = 0; i < table3.getColumnCount(); i++)  {  
            column3 = table3.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/  
            if(i == 0 || i == 2) {
            	column3.setPreferredWidth(150);
            } else if(i == 1) {
            	column3.setPreferredWidth(60);
            }  
            else {
            	column3.setPreferredWidth(40);
            }
        }
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table3.setPreferredScrollableViewportSize(getMinimumSize());
		/*用JScrollPane装载JTable，这样超出范围的列就可以通过滚动条来查看*/  
        JScrollPane scroll3 = new JScrollPane(table3);  
        scroll3.setSize(300, 200);
        scroll3.setBorder(new TitledBorder("权限详情表"));
        
        setLayout(new BorderLayout(5, 20));
        add(jp1, BorderLayout.WEST);
        JPanel jp_m_r = new JPanel(new BorderLayout(5, 20));
        jp_m_r.add(jp2, BorderLayout.WEST);
        jp_m_r.add(scroll3, BorderLayout.CENTER);
        add(jp_m_r, BorderLayout.CENTER);
	}
	public Dimension getPreferredSize() {
		return new Dimension(1024, 600);
	}
}

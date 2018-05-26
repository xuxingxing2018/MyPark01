package ui.mypark.com;
/* ��д�����ַ����
 * https://blog.csdn.net/fanjianwucmx/article/details/54882044 
 * 
 * ѧϰ��վ
 * http://www.w3school.com.cn/sql/sql_syntax.asp
 * 
 * ���������׵�mysql�汾�ţ�mysql 8.0.11 for win64
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.mysql.cj.xdevapi.Statement;

public class DataBase {
    // �������ݿ�����  com.mysql.jdbc.Driver
    private static String dbdriver = "com.mysql.cj.jdbc.Driver";
    // ��ȡmysql���ӵ�ַ
    private static String dburl = "jdbc:mysql://127.0.0.1:3306/sys?useSSL=false&serverTimezone=GMT%2B8";
    // ��������
    private static String username = "root";
    // ���ݿ�����
    private static String userpassword = "xxxelder123!!!4";
    // ��ȡһ�����ݵ�����
    public static Connection conn = null;
    // ��ȡ���ӵ�һ��״̬

    /*public static void main(String[] args) throws SQLException {
        List<List<Object>> x = getData("sys",
                "select FILE_NAME from FILES");
        System.out.println("x=" + x);
    }*/

    public void testDataBase(String myDataBaseName) {
    	try {
    		Class.forName(dbdriver);
    		String myjdbcUrl = dburl.replace("sys", myDataBaseName);
    		conn = DriverManager.getConnection(myjdbcUrl, username, userpassword);
    		conn.close();
    		mainJFrameUi.DataBaseLinkFlag = true;// ���ݿ�����������־λ
    	} catch (ClassNotFoundException e) {
        	System.out.println("�Ҳ������������� ����������ʧ�ܣ�1");
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
            e.printStackTrace();
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
        	// System.out.println("���ݿ�����ʧ��1");
        	int result;
        	result = JOptionPane.showConfirmDialog(null, "���ҵ�����������δ�������ݿ⣬�Ƿ񴴽������ݿ⣿", "���ݿ�����ʧ��", JOptionPane.YES_NO_OPTION);
        	if(result == 0) {// ���ˡ��ǡ���ť
        		createDataBase(myDataBaseName);
        	} else if(result == 2) {// ���ˡ��񡱰�ť
        		System.exit(0);
        	}
            e.printStackTrace();
        } finally {
            if (conn == null)
                return;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void createDataBase(String myDataBaseName) {
    	java.sql.Statement stat = null;
    	try {
    		Class.forName(dbdriver);
    		conn = DriverManager.getConnection(dburl, username, userpassword);
    		stat = conn.createStatement();
    		stat.executeUpdate("create database " + myDataBaseName);
    		//�򿪴��������ݿ�
            stat.close();
            conn.close();
            
            String myjdbcUrl = dburl.replace("sys", myDataBaseName);
            conn = DriverManager.getConnection(myjdbcUrl, username, userpassword);
            stat = conn.createStatement();
            //������SystemUser
            stat.executeUpdate("create table SystemUser(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "
            		+ "Password varchar(16), "
            		+ "RoleType varchar(1) not null);");
            //�������
            stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //������Car
            stat.executeUpdate("create table Car(id int not null AUTO_INCREMENT primary key, "
            		+ "CarNumber varchar(16) not null, "//���ƺ���
            		+ "CarType varchar(1) not null, "//���������ʱ������ѳ������⳵����ֵ�����ƴγ�
            		+ "CarClass int not null, "//�������A�ͳ���B�ͳ���C�ͳ���D�ͳ�
            		+ "StartDate DATE not null, "//��Ч����
            		+ "EndDate DATE not null, "//��Чֹ��
            		+ "ParkID int, "//ͣ��������Ȩ��
            		+ "Money int, "//�˻���0Ԫ
            		+ "CarModel varchar(8), "//����Ʒ�ƣ�������������⣬���ǵϣ����������ۣ��ֶ���
            		+ "CarColor varchar(4), "//������ɫ����ɫ����ɫ����ɫ
            		+ "Name varchar(12), "//��������������������
            		+ "IdentityCard varchar(20), "//���֤�ţ�341122200012120505
            		+ "Phone varchar(16), "//�绰���룬13699992222
            		+ "Address varchar(40), "//��ַ��5��A��Ԫ1604��
            		+ "LotNumber varchar(4), "//������λ
            		+ "ShareLotNumber int, "//��������������
            		+ "RegisterTime DATETIME, "//ע��ʱ��
            		+ "Remark varchar(40), "//��ע
            		+ "BannedFlag bit);");//�ѱ����ñ�ʶ=1���ѱ����ã�=0������
            //�������
            stat.executeUpdate("insert into Car values(1, '��B826BD', '1', '1', '2017-01-01', '2027-01-01', '1', "
            		+ "'0', 'volov', '��', '���Գ���1', '', '18688999698', '5B1302', 'B102', '0', '2018-05-07', '', b'0');");

            //������CarUnverify
            stat.executeUpdate("create table CarUnchecked(id int not null AUTO_INCREMENT primary key, "
            		+ "CarNumber varchar(16) not null, "//���ƺ���
            		+ "CarType varchar(1) not null, "//���������ʱ������ѳ������⳵����ֵ�����ƴγ�
            		+ "CarClass int not null, "//�������A�ͳ���B�ͳ���C�ͳ���D�ͳ�
            		+ "StartDate DATE not null, "//��Ч����
            		+ "EndDate DATE not null, "//��Чֹ��
            		+ "ParkID int, "//ͣ��������Ȩ��
            		+ "Money int, "//�˻���0Ԫ
            		+ "CarModel varchar(8), "//����Ʒ�ƣ�������������⣬���ǵϣ����������ۣ��ֶ���
            		+ "CarColor varchar(4), "//������ɫ����ɫ����ɫ����ɫ
            		+ "Name varchar(12), "//��������������������
            		+ "IdentityCard varchar(20), "//���֤�ţ�341122200012120505
            		+ "Phone varchar(16), "//�绰���룬13699992222
            		+ "Address varchar(40), "//��ַ��5��A��Ԫ1604��
            		+ "LotNumber varchar(4), "//������λ
            		+ "ShareLotNumber int, "//��������������
            		+ "RegisterTime DATETIME, "//ע��ʱ��
            		+ "Remark varchar(40), "//��ע
            		+ "BannedFlag bit);");//�ѱ����ñ�ʶ=1���ѱ����ã�=0������
            //�������
            //stat.executeUpdate("insert into user values(1, 'admin', 'admin', '0');");
            
            //������Way
            stat.executeUpdate("create table Way(id int not null AUTO_INCREMENT primary key, "
            		+ "In_Out bit not null, "//��ڻ���ڱ�ʶ
            		+ "Name varchar(12), "//��������
            		+ "ParkID int, "//ͣ��������Ȩ��
            		+ "BoardType varchar(1), "//���ư�����
            		+ "BoardIP varchar(15), "//���ư�IP
            		+ "CameraType varchar(1), "//�����������
            		+ "MainCameraIP varchar(15), "//�������IP
            		+ "MainCameraSendDataFlag bit, "//�������ת��ͨѶ��־λ
            		+ "SecondCameraIP varchar(15), "//�������IP
            		+ "LedScreenType varchar(1), "//��λ��ʾ������
            		+ "LedScreenIP varchar(15), "//��λ��ʾ��IP
            		+ "CardSenderType varchar(1), "//����������
            		+ "CardSenderPort varchar(6), "//�������˿�
            		+ "CardSenderParameter varchar(16), "//������ͨѶ����
            		+ "PeopleCheckFlag bit);");//��Ҫ�˹�ȷ�ϱ�־λ��=1����Ҫ�˹�ȷ�Ϸ���                                                                                                                                                                                                                    
            //�������
            stat.executeUpdate("insert into Way values(1, b'0', '1#���', '1', "
            		+ "'1', '192.168.1.131', "
            		+ "'1', '192.168.1.31', b'0', '', "
            		+ "'', '', '', '', '', b'0');");
            stat.executeUpdate("insert into Way values(2, b'1', '2#����', '1', "
            		+ "'1', '192.168.1.132', "
            		+ "'1', '192.168.1.32', b'0', '', "
            		+ "'', '', '', '', '', b'0');");
            
            //������Park
            stat.executeUpdate("create table Park(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//ͣ��������������
            		+ "AllLots int not null default 1000, "//�ܳ�λ
            		+ "TempLots int default 500, "//��ʱ��λ
            		+ "FreeFlag bit, "//��ѱ�־
            		+ "Layer char(1), "//�������ڲ㣬0��ʾ���棬1��ʾ��һ�㣬2��ʾ������
            		+ "Remark varchar(40));");//��ע
            //�������
            //stat.executeUpdate("insert into Computer values(1, 'admin', 'admin', '0');");
            
            //������Computer
            stat.executeUpdate("create table Computer(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//���Ƶ��Ե���������
            		+ "ComputerIP varchar(15), "//����IP
            		+ "WayIn1 int, WayIn2 int, WayIn3 int, WayIn4 int, WayIn5 int, WayIn6 int, WayIn7 int, WayIn8 int, "//8·��ڳ������
            		+ "WayOut1 int, WayOut2 int, WayOut3 int, WayOut4 int, WayOut5 int, WayOut6 int, WayOut7 int, WayOut8 int, "//8·���ڳ������
            		+ "Remark varchar(40));");//��ע
            //�������
            //stat.executeUpdate("insert into Computer values(1, 'admin', 'admin', '0');");
            
            //������CarClass
            stat.executeUpdate("create table CarClassABCD(Name int not null AUTO_INCREMENT primary key, "
            		+ "TempCarPayMethod int, "//��ʱ���Ʒѹ�ʽ
            		+ "DateCarPayMethod int, "//���⳵�Ʒѹ�ʽ
            		+ "FreeCarPayMethod int, "//��ѳ��Ʒѹ�ʽ
            		+ "PaidCarPayMethod int, "//��ֵ���Ʒѹ�ʽ
            		+ "CountCarPayMethod int, "//�ƴγ��Ʒѹ�ʽ
            		+ "CarPlateColor varchar(1));");//�����ͳ�ר��������ɫ��0��ʾ���ƣ�1��ʾ���ƣ�2��ʾ���ƣ�3��ʾ���ƣ�4��ʾ����Դ�����̣�5��ʾ����Դ����˫ƴ
            //�������
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");

            //������CountFeeMethod
            stat.executeUpdate("create table CountFeeMethod(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//�շѱ�׼����
            		+ "Type varchar(1) not null, "//�շѱ�׼���ͣ�1����ʽ�շѱ�׼��2���ѭ��ʽ�շѱ�׼��3���ʽ�շѱ�׼
            		+ "FreeTime int,"//���ʱ��
            		+ "Sector1StartTime TIME, "//��һʱ�ο�ʼʱ��
            		+ "Sector1EndTime TIME, "//��һʱ�ν���ʱ��
            		+ "Sector1FirstTime int, Sector1FirstFee int, Sector1AddTime int, Sector1AddFee int, Sector1MaxFee int, "
            		+ "Sector2FirstTime int, Sector2FirstFee int, Sector2AddTime int, Sector2AddFee int, Sector2MaxFee int, "
            		+ "MixSectorFeeTime int, "//������ʱ��
            		+ "AllDayMaxFee int, "//ȫ������շ�
            		+ "Sector1Time int, Sector2Time int, Sector3Time int, Sector4Time int, Sector5Time int, Sector6Time int, "
            		+ "Sector7Time int, Sector8Time int, Sector9Time int, Sector10Time int, Sector11Time int, Sector12Time int, "
            		+ "Sector13Time int, Sector14Time int, Sector15Time int, Sector16Time int, Sector17Time int, Sector18Time int, "
            		+ "Sector19Time int, Sector20Time int, Sector21Time int, Sector22Time int, Sector23Time int, Sector24Time int, "
            		+ "LoopStartSectorNumber int, "//ѭ����ʼ�Ķκ�
            		+ "ProgramMethod TEXT), "//����շѹ�ʽ����
            		+ "Remark varchar(40);");//��ע
            //�������
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");

            //������ShareLot
            stat.executeUpdate("create table ShareLot(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "
            		+ "LotNumber int);");
            //�������
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //������Shop
            stat.executeUpdate("create table Shop(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//�̻�����
            		+ "DiscountType varchar(1), "//�Żݷ�ʽ
            		+ "DiscountData int);");//�Ż�����
            //�������
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //������Parameter
            stat.executeUpdate("create table Parameter(id int not null AUTO_INCREMENT primary key, "
            		+ "ProvinceName varchar(1), "//���س��ƺ���
            		+ "IgnoreProvinceFlag bit, "//���Ժ��ֱ�־λ��=1��ȶ�ʱ���Ժ���
            		+ "FuzzyMatchingIn int, "//�볡ģ��ƥ��λ��
            		+ "FuzzyMatchingOut int, "//����ģ��ƥ��λ��
            		+ "TempCarAutoNumberFlag bit, "//δʶ�����ʱ���Զ����������볡��־λ��=1��δʶ����Զ��������볡
            		+ "ConfidenceLevel int, "//���ŶȲ����ڴ�ֵ����Ϊ����
            		+ "AllowTempCarScanInFlag bit, "//������ʱ��ʶ���볡��־λ
            		+ "AllowTempCarScanOutFlag bit, "//������ʱ��ʶ�������־λ
            		+ "AllowImportInFlag bit, "//����¼�복���볡��־λ
            		+ "AllowImportOutFlag bit, "//����¼�복�Ƴ�����־λ
            		+ "PaidCarNoMoneyInFlag bit, "//��ֵ�����Ϊ0�����볡��־λ
            		+ "PaidCarNoMoneyOut char(1), "//��ֵ�����Ϊ0����ѡ��=0�Ʒѳ�����=1��ֹ����
            		+ "BespeakCarCountFeeFlag bit, "//ԤԼ����Ч����Ʒѱ�־λ��
            		+ "AllowFreeOutFlag bit, "//������ѷ��б�־λ��
            		+ "AllowGiveUpFlag bit, "//������������־λ��
            		+ "AllowOpenButtonInFlag bit, "//��������ֹ���բ��־λ��
            		+ "AllowOpenButtonOutFlag bit, "//��������ֹ���բ��־λ��
            		+ "AllowTempCarRepeatIn char(1), "//������ʱ���ظ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "AllowDateCarRepeatIn char(1), "//�������⳵�ظ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "AllowFreeCarRepeatIn char(1), "//������ѳ��ظ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "AllowPaidCarRepeatIn char(1), "//����ֵ���ظ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "AllowCountCarRepeatIn char(1), "//����ƴγ��ظ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "AllowTempCarRepeatOut char(1), "//������ʱ���ظ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "AllowDateCarRepeatOut char(1), "//�������⳵�ظ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "AllowFreeCarRepeatOut char(1), "//������ѳ��ظ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "AllowPaidCarRepeatOut char(1), "//����ֵ���ظ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "AllowCountCarRepeatOut char(1), "//����ƴγ��ظ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "TempCarNoLotIn char(1), "//��λ����ʱ����ʱ���볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "DateCarNoLotIn char(1), "//��λ����ʱ�����⳵�볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "FreeCarNoLotIn char(1), "//��λ����ʱ����ѳ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "PaidCarNoLotIn char(1), "//��λ����ʱ����ֵ���볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "CountCarNoLotIn char(1), "//��λ����ʱ���ƴγ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "SameCarTime int default 15, "//ͬ���ƹ��˼��ʱ�䣬��λ��
            		+ "SameCardTime int default 15, "//ͬ��Ƭ���˼��ʱ�䣬��λ��
            		+ "AllowTempCarInStartTime TIME defualt 00:00:00, "//������ʱ���볡��ʼʱ��
            		+ "AllowTempCarInEndTime TIME defualt 00:00:00, "//������ʱ���볡����ʱ��
            		+ "AllowDateCarInStartTime TIME defualt 00:00:00, "//�������⳵�볡��ʼʱ��
            		+ "AllowDateCarInEndTime TIME defualt 00:00:00, "//�������⳵�볡����ʱ��
            		+ "AllowFreeCarInStartTime TIME defualt 00:00:00, "//������ѳ��볡��ʼʱ��
            		+ "AllowFreeCarInEndTime TIME defualt 00:00:00, "//������ѳ��볡����ʱ��
            		+ "AllowPaidCarInStartTime TIME defualt 00:00:00, "//����ֵ���볡��ʼʱ��
            		+ "AllowPaidCarInEndTime TIME defualt 00:00:00, "//����ֵ���볡����ʱ��
            		+ "AllowCountCarInStartTime TIME defualt 00:00:00, "//����ƴγ��볡��ʼʱ��
            		+ "AllowCountCarInEndTime TIME defualt 00:00:00, "//����ƴγ��볡����ʱ��
            		+ "AllowTempCarOutStartTime TIME defualt 00:00:00, "//������ʱ��������ʼʱ��
            		+ "AllowTempCarOutEndTime TIME defualt 00:00:00, "//������ʱ����������ʱ��
            		+ "AllowDateCarOutStartTime TIME defualt 00:00:00, "//�������⳵������ʼʱ��
            		+ "AllowDateCarOutEndTime TIME defualt 00:00:00, "//�������⳵��������ʱ��
            		+ "AllowFreeCarOutStartTime TIME defualt 00:00:00, "//������ѳ�������ʼʱ��
            		+ "AllowFreeCarOutEndTime TIME defualt 00:00:00, "//������ѳ���������ʱ��
            		+ "AllowPaidCarOutStartTime TIME defualt 00:00:00, "//����ֵ��������ʼʱ��
            		+ "AllowPaidCarOutEndTime TIME defualt 00:00:00, "//����ֵ����������ʱ��
            		+ "AllowCountCarOutStartTime TIME defualt 00:00:00, "//����ƴγ�������ʼʱ��
            		+ "AllowCountCarOutEndTime TIME defualt 00:00:00, "//����ƴγ���������ʱ��
            		+ "TempCarNotTimeIn char(1), "//���������볡ʱ�����ʱ����ʱ���볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "DateCarNotTimeIn char(1), "//���������볡ʱ�����ʱ�����⳵�볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "FreeCarNotTimeIn char(1), "//���������볡ʱ�����ʱ����ѳ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "PaidCarNotTimeIn char(1), "//���������볡ʱ�����ʱ����ֵ���볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "CountCarNotTimeIn char(1), "//���������볡ʱ�����ʱ���ƴγ��볡��ʽ��=0ֱ���볡��=1����ȷ�ϣ�=2��ֹ�볡
            		+ "TempCarNotTimeOut char(1), "//�����������ʱ�����ʱ����ʱ��������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "DateCarNotTimeOut char(1), "//�����������ʱ�����ʱ�����⳵������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "FreeCarNotTimeOut char(1), "//�����������ʱ�����ʱ����ѳ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "PaidCarNotTimeOut char(1), "//�����������ʱ�����ʱ����ֵ��������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "CountCarNotTimeOut char(1), "//�����������ʱ�����ʱ���ƴγ�������ʽ��=0ֱ�ӳ�����=1����ȷ�ϣ�=2��ֹ����
            		+ "TempCarOrCardInFlag bit, "//��ʱ���������볡��־λ��=0�����ƣ�1��+����
            		+ "TempCarOrCardOutFlag bit, "//��ʱ�������Ƴ�����־λ��=0�����ƣ�1��+����
            		+ "TempCarOrCardInFlag bit, "//��ʱ���������볡��־λ��=0�����ƣ�1��+����
            		+ "TempCarOrCardOutFlag bit);");//��ʱ�������Ƴ�����־λ��=0�����ƣ�1��+����
            //�������
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            
            //��ѯ����
            ResultSet result = stat.executeQuery("select * from user;");
            while (result.next())
            {
                System.out.println(result.getInt("id") + " " + result.getString("name"));
            }

            //�ر����ݿ�
            result.close();
            stat.close();
            conn.close(); 
            mainJFrameUi.DataBaseLinkFlag = true;// ���ݿ�����������־λ
    	} catch (ClassNotFoundException e) {
        	System.out.println("�Ҳ������������� ����������ʧ�ܣ�2");
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
            e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("���ݿ�����ʧ��2");
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
        	//throw e;
        	e.printStackTrace();
        } 
    }
    /**
     * ��ȡ���ݿ�����
     * 
     * @param myProjName
     * @return
     */
    private static Connection getConn(String myDataBaseName) {
        Connection conn = null;
        try {
            Class.forName(dbdriver);            
            String myjdbcUrl = dburl.replace("sys", myDataBaseName);
            conn = DriverManager.getConnection(myjdbcUrl, username, userpassword);
            mainJFrameUi.DataBaseLinkFlag = true;// ���ݿ�����������־λ
        } catch (ClassNotFoundException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
        	System.out.println("�Ҳ������������� ����������ʧ�ܣ�3");
            e.printStackTrace();
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
        	System.out.println("���ݿ�����ʧ��3");
        	e.printStackTrace();
        }
        return conn;
    }
    /**
     * �ر����ݿ�����
     * 
     * @param rs
     * @param ps
     * @param conn
     */
    private static void closeAll(ResultSet rs, PreparedStatement ps,
            Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn == null)
            return;
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * ��������е��б�ÿ���б��а����е��б�
     * 
     * @param ProjName
     * @param sql
     * @return
     */
    public static List<List<Object>> getData(String myDataBaseName, String sql) {
        Connection conn = getConn(myDataBaseName);
        PreparedStatement ps = null;
        List<List<Object>> list = new ArrayList<List<Object>>();
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columnCount = md.getColumnCount();
            while (rs.next()) {
                List<Object> lst = new ArrayList<Object>();
                for (int i = 1; i <= columnCount; ++i) {
                    lst.add(rs.getObject(i) == null ? "" : rs.getObject(i));
                }
                list.add(lst);
            }
            mainJFrameUi.DataBaseLinkFlag = true;// ���ݿ�����������־λ
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// ���ݿ�����������־λ
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }
}
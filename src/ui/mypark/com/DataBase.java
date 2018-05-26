package ui.mypark.com;
/* 抄写借鉴网址如下
 * https://blog.csdn.net/fanjianwucmx/article/details/54882044 
 * 
 * 学习网站
 * http://www.w3school.com.cn/sql/sql_syntax.asp
 * 
 * 本程序配套的mysql版本号：mysql 8.0.11 for win64
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
    // 加载数据库驱动  com.mysql.jdbc.Driver
    private static String dbdriver = "com.mysql.cj.jdbc.Driver";
    // 获取mysql连接地址
    private static String dburl = "jdbc:mysql://127.0.0.1:3306/sys?useSSL=false&serverTimezone=GMT%2B8";
    // 数据名称
    private static String username = "root";
    // 数据库密码
    private static String userpassword = "xxxelder123!!!4";
    // 获取一个数据的连接
    public static Connection conn = null;
    // 获取连接的一个状态

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
    		mainJFrameUi.DataBaseLinkFlag = true;// 数据库连接正常标志位
    	} catch (ClassNotFoundException e) {
        	System.out.println("找不到驱动程序类 ，加载驱动失败！1");
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
            e.printStackTrace();
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
        	// System.out.println("数据库连接失败1");
        	int result;
        	result = JOptionPane.showConfirmDialog(null, "已找到服务器，但未发现数据库，是否创建新数据库？", "数据库连接失败", JOptionPane.YES_NO_OPTION);
        	if(result == 0) {// 按了“是”按钮
        		createDataBase(myDataBaseName);
        	} else if(result == 2) {// 按了“否”按钮
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
    		//打开创建的数据库
            stat.close();
            conn.close();
            
            String myjdbcUrl = dburl.replace("sys", myDataBaseName);
            conn = DriverManager.getConnection(myjdbcUrl, username, userpassword);
            stat = conn.createStatement();
            //创建表SystemUser
            stat.executeUpdate("create table SystemUser(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "
            		+ "Password varchar(16), "
            		+ "RoleType varchar(1) not null);");
            //添加数据
            stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //创建表Car
            stat.executeUpdate("create table Car(id int not null AUTO_INCREMENT primary key, "
            		+ "CarNumber varchar(16) not null, "//车牌号码
            		+ "CarType varchar(1) not null, "//车辆类别，临时车、免费车、月租车、储值车，计次车
            		+ "CarClass int not null, "//车辆类别，A型车，B型车，C型车，D型车
            		+ "StartDate DATE not null, "//有效起日
            		+ "EndDate DATE not null, "//有效止日
            		+ "ParkID int, "//停车场出入权限
            		+ "Money int, "//账户余额，0元
            		+ "CarModel varchar(8), "//车辆品牌，丰田，宝骏，五菱，比亚迪，长安，奔驰，沃尔沃
            		+ "CarColor varchar(4), "//车辆颜色，白色，红色，黑色
            		+ "Name varchar(12), "//车主姓名，张三，李四
            		+ "IdentityCard varchar(20), "//身份证号，341122200012120505
            		+ "Phone varchar(16), "//电话号码，13699992222
            		+ "Address varchar(40), "//地址，5栋A单元1604室
            		+ "LotNumber varchar(4), "//所属车位
            		+ "ShareLotNumber int, "//所属车辆分组编号
            		+ "RegisterTime DATETIME, "//注册时间
            		+ "Remark varchar(40), "//备注
            		+ "BannedFlag bit);");//已被禁用标识=1则已被禁用，=0则正常
            //添加数据
            stat.executeUpdate("insert into Car values(1, '粤B826BD', '1', '1', '2017-01-01', '2027-01-01', '1', "
            		+ "'0', 'volov', '黑', '测试车牌1', '', '18688999698', '5B1302', 'B102', '0', '2018-05-07', '', b'0');");

            //创建表CarUnverify
            stat.executeUpdate("create table CarUnchecked(id int not null AUTO_INCREMENT primary key, "
            		+ "CarNumber varchar(16) not null, "//车牌号码
            		+ "CarType varchar(1) not null, "//车辆类别，临时车、免费车、月租车、储值车，计次车
            		+ "CarClass int not null, "//车辆类别，A型车，B型车，C型车，D型车
            		+ "StartDate DATE not null, "//有效起日
            		+ "EndDate DATE not null, "//有效止日
            		+ "ParkID int, "//停车场出入权限
            		+ "Money int, "//账户余额，0元
            		+ "CarModel varchar(8), "//车辆品牌，丰田，宝骏，五菱，比亚迪，长安，奔驰，沃尔沃
            		+ "CarColor varchar(4), "//车辆颜色，白色，红色，黑色
            		+ "Name varchar(12), "//车主姓名，张三，李四
            		+ "IdentityCard varchar(20), "//身份证号，341122200012120505
            		+ "Phone varchar(16), "//电话号码，13699992222
            		+ "Address varchar(40), "//地址，5栋A单元1604室
            		+ "LotNumber varchar(4), "//所属车位
            		+ "ShareLotNumber int, "//所属车辆分组编号
            		+ "RegisterTime DATETIME, "//注册时间
            		+ "Remark varchar(40), "//备注
            		+ "BannedFlag bit);");//已被禁用标识=1则已被禁用，=0则正常
            //添加数据
            //stat.executeUpdate("insert into user values(1, 'admin', 'admin', '0');");
            
            //创建表Way
            stat.executeUpdate("create table Way(id int not null AUTO_INCREMENT primary key, "
            		+ "In_Out bit not null, "//入口或出口标识
            		+ "Name varchar(12), "//车道名称
            		+ "ParkID int, "//停车场出入权限
            		+ "BoardType varchar(1), "//控制板类型
            		+ "BoardIP varchar(15), "//控制板IP
            		+ "CameraType varchar(1), "//主摄像机类型
            		+ "MainCameraIP varchar(15), "//主摄像机IP
            		+ "MainCameraSendDataFlag bit, "//主摄像机转发通讯标志位
            		+ "SecondCameraIP varchar(15), "//副摄像机IP
            		+ "LedScreenType varchar(1), "//满位显示屏类型
            		+ "LedScreenIP varchar(15), "//满位显示屏IP
            		+ "CardSenderType varchar(1), "//发卡器类型
            		+ "CardSenderPort varchar(6), "//发卡器端口
            		+ "CardSenderParameter varchar(16), "//发卡器通讯参数
            		+ "PeopleCheckFlag bit);");//需要人工确认标志位，=1则需要人工确认放行                                                                                                                                                                                                                    
            //添加数据
            stat.executeUpdate("insert into Way values(1, b'0', '1#入口', '1', "
            		+ "'1', '192.168.1.131', "
            		+ "'1', '192.168.1.31', b'0', '', "
            		+ "'', '', '', '', '', b'0');");
            stat.executeUpdate("insert into Way values(2, b'1', '2#出口', '1', "
            		+ "'1', '192.168.1.132', "
            		+ "'1', '192.168.1.32', b'0', '', "
            		+ "'', '', '', '', '', b'0');");
            
            //创建表Park
            stat.executeUpdate("create table Park(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//停车场的自由命名
            		+ "AllLots int not null default 1000, "//总车位
            		+ "TempLots int default 500, "//临时车位
            		+ "FreeFlag bit, "//免费标志
            		+ "Layer char(1), "//车场所在层，0表示地面，1表示负一层，2表示负二层
            		+ "Remark varchar(40));");//备注
            //添加数据
            //stat.executeUpdate("insert into Computer values(1, 'admin', 'admin', '0');");
            
            //创建表Computer
            stat.executeUpdate("create table Computer(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//控制电脑的自由命名
            		+ "ComputerIP varchar(15), "//电脑IP
            		+ "WayIn1 int, WayIn2 int, WayIn3 int, WayIn4 int, WayIn5 int, WayIn6 int, WayIn7 int, WayIn8 int, "//8路入口车道编号
            		+ "WayOut1 int, WayOut2 int, WayOut3 int, WayOut4 int, WayOut5 int, WayOut6 int, WayOut7 int, WayOut8 int, "//8路出口车道编号
            		+ "Remark varchar(40));");//备注
            //添加数据
            //stat.executeUpdate("insert into Computer values(1, 'admin', 'admin', '0');");
            
            //创建表CarClass
            stat.executeUpdate("create table CarClassABCD(Name int not null AUTO_INCREMENT primary key, "
            		+ "TempCarPayMethod int, "//临时车计费公式
            		+ "DateCarPayMethod int, "//月租车计费公式
            		+ "FreeCarPayMethod int, "//免费车计费公式
            		+ "PaidCarPayMethod int, "//储值车计费公式
            		+ "CountCarPayMethod int, "//计次车计费公式
            		+ "CarPlateColor varchar(1));");//本类型车专属车牌颜色，0表示蓝牌，1表示黑牌，2表示白牌，3表示黄牌，4表示新能源渐变绿，5表示新能源黄绿双拼
            //添加数据
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");

            //创建表CountFeeMethod
            stat.executeUpdate("create table CountFeeMethod(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//收费标准名称
            		+ "Type varchar(1) not null, "//收费标准类型，1两段式收费标准，2多段循环式收费标准，3编程式收费标准
            		+ "FreeTime int,"//免费时间
            		+ "Sector1StartTime TIME, "//第一时段开始时间
            		+ "Sector1EndTime TIME, "//第一时段结束时间
            		+ "Sector1FirstTime int, Sector1FirstFee int, Sector1AddTime int, Sector1AddFee int, Sector1MaxFee int, "
            		+ "Sector2FirstTime int, Sector2FirstFee int, Sector2AddTime int, Sector2AddFee int, Sector2MaxFee int, "
            		+ "MixSectorFeeTime int, "//跨段免费时间
            		+ "AllDayMaxFee int, "//全天最高收费
            		+ "Sector1Time int, Sector2Time int, Sector3Time int, Sector4Time int, Sector5Time int, Sector6Time int, "
            		+ "Sector7Time int, Sector8Time int, Sector9Time int, Sector10Time int, Sector11Time int, Sector12Time int, "
            		+ "Sector13Time int, Sector14Time int, Sector15Time int, Sector16Time int, Sector17Time int, Sector18Time int, "
            		+ "Sector19Time int, Sector20Time int, Sector21Time int, Sector22Time int, Sector23Time int, Sector24Time int, "
            		+ "LoopStartSectorNumber int, "//循环开始的段号
            		+ "ProgramMethod TEXT), "//编程收费公式代码
            		+ "Remark varchar(40);");//备注
            //添加数据
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");

            //创建表ShareLot
            stat.executeUpdate("create table ShareLot(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "
            		+ "LotNumber int);");
            //添加数据
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //创建表Shop
            stat.executeUpdate("create table Shop(id int not null AUTO_INCREMENT primary key, "
            		+ "Name varchar(12) not null, "//商户名称
            		+ "DiscountType varchar(1), "//优惠方式
            		+ "DiscountData int);");//优惠数额
            //添加数据
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            //创建表Parameter
            stat.executeUpdate("create table Parameter(id int not null AUTO_INCREMENT primary key, "
            		+ "ProvinceName varchar(1), "//本地车牌汉字
            		+ "IgnoreProvinceFlag bit, "//忽略汉字标志位，=1则比对时忽略汉字
            		+ "FuzzyMatchingIn int, "//入场模糊匹配位数
            		+ "FuzzyMatchingOut int, "//出场模糊匹配位数
            		+ "TempCarAutoNumberFlag bit, "//未识别的临时车自动编制临牌入场标志位，=1则未识别就自动编临牌入场
            		+ "ConfidenceLevel int, "//置信度不低于此值则认为可信
            		+ "AllowTempCarScanInFlag bit, "//允许临时车识别入场标志位
            		+ "AllowTempCarScanOutFlag bit, "//允许临时车识别出场标志位
            		+ "AllowImportInFlag bit, "//允许录入车牌入场标志位
            		+ "AllowImportOutFlag bit, "//允许录入车牌出场标志位
            		+ "PaidCarNoMoneyInFlag bit, "//储值车余额为0允许入场标志位
            		+ "PaidCarNoMoneyOut char(1), "//储值车余额为0出场选择，=0计费出场，=1禁止出场
            		+ "BespeakCarCountFeeFlag bit, "//预约车有效期外计费标志位，
            		+ "AllowFreeOutFlag bit, "//允许免费放行标志位，
            		+ "AllowGiveUpFlag bit, "//允许放弃处理标志位，
            		+ "AllowOpenButtonInFlag bit, "//允许入口手工开闸标志位，
            		+ "AllowOpenButtonOutFlag bit, "//允许出口手工开闸标志位，
            		+ "AllowTempCarRepeatIn char(1), "//允许临时车重复入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "AllowDateCarRepeatIn char(1), "//允许月租车重复入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "AllowFreeCarRepeatIn char(1), "//允许免费车重复入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "AllowPaidCarRepeatIn char(1), "//允许储值车重复入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "AllowCountCarRepeatIn char(1), "//允许计次车重复入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "AllowTempCarRepeatOut char(1), "//允许临时车重复出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "AllowDateCarRepeatOut char(1), "//允许月租车重复出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "AllowFreeCarRepeatOut char(1), "//允许免费车重复出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "AllowPaidCarRepeatOut char(1), "//允许储值车重复出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "AllowCountCarRepeatOut char(1), "//允许计次车重复出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "TempCarNoLotIn char(1), "//车位已满时，临时车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "DateCarNoLotIn char(1), "//车位已满时，月租车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "FreeCarNoLotIn char(1), "//车位已满时，免费车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "PaidCarNoLotIn char(1), "//车位已满时，储值车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "CountCarNoLotIn char(1), "//车位已满时，计次车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "SameCarTime int default 15, "//同车牌过滤间隔时间，单位秒
            		+ "SameCardTime int default 15, "//同卡片过滤间隔时间，单位秒
            		+ "AllowTempCarInStartTime TIME defualt 00:00:00, "//允许临时车入场开始时间
            		+ "AllowTempCarInEndTime TIME defualt 00:00:00, "//允许临时车入场结束时间
            		+ "AllowDateCarInStartTime TIME defualt 00:00:00, "//允许月租车入场开始时间
            		+ "AllowDateCarInEndTime TIME defualt 00:00:00, "//允许月租车入场结束时间
            		+ "AllowFreeCarInStartTime TIME defualt 00:00:00, "//允许免费车入场开始时间
            		+ "AllowFreeCarInEndTime TIME defualt 00:00:00, "//允许免费车入场结束时间
            		+ "AllowPaidCarInStartTime TIME defualt 00:00:00, "//允许储值车入场开始时间
            		+ "AllowPaidCarInEndTime TIME defualt 00:00:00, "//允许储值车入场结束时间
            		+ "AllowCountCarInStartTime TIME defualt 00:00:00, "//允许计次车入场开始时间
            		+ "AllowCountCarInEndTime TIME defualt 00:00:00, "//允许计次车入场结束时间
            		+ "AllowTempCarOutStartTime TIME defualt 00:00:00, "//允许临时车出场开始时间
            		+ "AllowTempCarOutEndTime TIME defualt 00:00:00, "//允许临时车出场结束时间
            		+ "AllowDateCarOutStartTime TIME defualt 00:00:00, "//允许月租车出场开始时间
            		+ "AllowDateCarOutEndTime TIME defualt 00:00:00, "//允许月租车出场结束时间
            		+ "AllowFreeCarOutStartTime TIME defualt 00:00:00, "//允许免费车出场开始时间
            		+ "AllowFreeCarOutEndTime TIME defualt 00:00:00, "//允许免费车出场结束时间
            		+ "AllowPaidCarOutStartTime TIME defualt 00:00:00, "//允许储值车出场开始时间
            		+ "AllowPaidCarOutEndTime TIME defualt 00:00:00, "//允许储值车出场结束时间
            		+ "AllowCountCarOutStartTime TIME defualt 00:00:00, "//允许计次车出场开始时间
            		+ "AllowCountCarOutEndTime TIME defualt 00:00:00, "//允许计次车出场结束时间
            		+ "TempCarNotTimeIn char(1), "//不在允许入场时间段内时，临时车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "DateCarNotTimeIn char(1), "//不在允许入场时间段内时，月租车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "FreeCarNotTimeIn char(1), "//不在允许入场时间段内时，免费车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "PaidCarNotTimeIn char(1), "//不在允许入场时间段内时，储值车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "CountCarNotTimeIn char(1), "//不在允许入场时间段内时，计次车入场方式，=0直接入场，=1弹窗确认，=2禁止入场
            		+ "TempCarNotTimeOut char(1), "//不在允许出场时间段内时，临时车出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "DateCarNotTimeOut char(1), "//不在允许出场时间段内时，月租车出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "FreeCarNotTimeOut char(1), "//不在允许出场时间段内时，免费车出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "PaidCarNotTimeOut char(1), "//不在允许出场时间段内时，储值车出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "CountCarNotTimeOut char(1), "//不在允许出场时间段内时，计次车出场方式，=0直接出场，=1弹窗确认，=2禁止出场
            		+ "TempCarOrCardInFlag bit, "//临时车卡或车牌入场标志位，=0卡或车牌，1卡+车牌
            		+ "TempCarOrCardOutFlag bit, "//临时车卡或车牌出场标志位，=0卡或车牌，1卡+车牌
            		+ "TempCarOrCardInFlag bit, "//临时车卡或车牌入场标志位，=0卡或车牌，1卡+车牌
            		+ "TempCarOrCardOutFlag bit);");//临时车卡或车牌出场标志位，=0卡或车牌，1卡+车牌
            //添加数据
            //stat.executeUpdate("insert into SystemUser values(1, 'admin', 'admin', '0');");
            
            
            //查询数据
            ResultSet result = stat.executeQuery("select * from user;");
            while (result.next())
            {
                System.out.println(result.getInt("id") + " " + result.getString("name"));
            }

            //关闭数据库
            result.close();
            stat.close();
            conn.close(); 
            mainJFrameUi.DataBaseLinkFlag = true;// 数据库连接正常标志位
    	} catch (ClassNotFoundException e) {
        	System.out.println("找不到驱动程序类 ，加载驱动失败！2");
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
            e.printStackTrace();
        } catch (SQLException e) {
        	System.out.println("数据库连接失败2");
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
        	//throw e;
        	e.printStackTrace();
        } 
    }
    /**
     * 获取数据库连接
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
            mainJFrameUi.DataBaseLinkFlag = true;// 数据库连接正常标志位
        } catch (ClassNotFoundException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
        	System.out.println("找不到驱动程序类 ，加载驱动失败！3");
            e.printStackTrace();
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
        	System.out.println("数据库连接失败3");
        	e.printStackTrace();
        }
        return conn;
    }
    /**
     * 关闭数据库连接
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
     * 查表，返回行的列表，每个列表中包含列的列表。
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
            mainJFrameUi.DataBaseLinkFlag = true;// 数据库连接正常标志位
        } catch (SQLException e) {
        	mainJFrameUi.DataBaseLinkFlag = false;// 数据库连接正常标志位
            e.printStackTrace();
        } finally {
            closeAll(rs, ps, conn);
        }
        return list;
    }
}
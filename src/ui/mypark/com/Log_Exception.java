package ui.mypark.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.List;  
  
/** 
 * Created by CXC on 2017/6/5. 
 */  
public class Log_Exception {  
    /** 
     * @将错误信息输入到txt中 
     * @param path 
     * @throws IOException 
     */  
	public void writeEror_to_txt(String path,String content) throws IOException{
        File F=new File(path);  
        //如果文件不存在,就动态创建文件  
        if(!F.exists()){  
            F.createNewFile();  
        }  
        FileWriter fw=null;  
        String writeDate="时间:"+this.get_nowDate()+"---"+"error:"+content;  
        try {  
            //设置为:True,表示写入的时候追加数据  
            fw=new FileWriter(F, true);  
            //回车并换行  
            fw.write(writeDate+"\r\n");  
        } catch (IOException e) {  
            e.printStackTrace();
        }finally{  
            if(fw!=null){  
                fw.close();  
            }  
        }  
    }  

	public void writeDat_to_txt(String path,String content) throws IOException{
        File F=new File(path);  
        //如果文件不存在,就动态创建文件  
        if(!F.exists()){  
            F.createNewFile();  
        }  
        FileWriter fw=null;  
        try {  
            //设置为:True,表示写入的时候追加数据  
            fw=new FileWriter(F, true);  
            //回车并换行  
            fw.write(content);  
        } catch (IOException e) {  
            e.printStackTrace();
        }finally{  
            if(fw!=null){  
                fw.close();  
            }  
        }  
    }  
	
	public String readDat_from_txt(String path, int line_number) throws IOException{
		String line = null;
        File F=new File(path);  
        //如果文件不存在,就动态创建文件  
        if(!F.exists()){  
            return line;  
        }  
        FileWriter fw=null;  
        try {  
            //设置为:True,表示写入的时候追加数据  
            fw=new FileWriter(F, true);  
            //回车并换行  
            InputStreamReader reader = new InputStreamReader(new FileInputStream(F)); // 建立一个输入流对象reader  
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言  
              
            line = br.readLine();
            int i = 1;
            while (line != null) {  
            	line = br.readLine(); // 一次读入一行数据 
                if(i == line_number) {
                	break;
                }
            	i++;
            }
        } catch (IOException e) {  
            e.printStackTrace();
        }finally{  
            if(fw!=null){  
                fw.close();  
            }
        }
        return line;
    }  
	
	/** 
     * @获取系统当前时间 
     * @return 
     */  
    public  String get_nowDate(){  
  
        Calendar D=Calendar.getInstance();  
        int year=0;  
        int moth=0;  
        int day=0;  
        year=D.get(Calendar.YEAR);  
        moth=D.get(Calendar.MONTH)+1;  
        day=D.get(Calendar.DAY_OF_MONTH);  
        String now_date=String.valueOf(year)+"-"+String.valueOf(moth)+"-"+String.valueOf(day);  
        return now_date;  
    }  
} 
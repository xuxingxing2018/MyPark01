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
     * @��������Ϣ���뵽txt�� 
     * @param path 
     * @throws IOException 
     */  
	public void writeEror_to_txt(String path,String content) throws IOException{
        File F=new File(path);  
        //����ļ�������,�Ͷ�̬�����ļ�  
        if(!F.exists()){  
            F.createNewFile();  
        }  
        FileWriter fw=null;  
        String writeDate="ʱ��:"+this.get_nowDate()+"---"+"error:"+content;  
        try {  
            //����Ϊ:True,��ʾд���ʱ��׷������  
            fw=new FileWriter(F, true);  
            //�س�������  
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
        //����ļ�������,�Ͷ�̬�����ļ�  
        if(!F.exists()){  
            F.createNewFile();  
        }  
        FileWriter fw=null;  
        try {  
            //����Ϊ:True,��ʾд���ʱ��׷������  
            fw=new FileWriter(F, true);  
            //�س�������  
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
        //����ļ�������,�Ͷ�̬�����ļ�  
        if(!F.exists()){  
            return line;  
        }  
        FileWriter fw=null;  
        try {  
            //����Ϊ:True,��ʾд���ʱ��׷������  
            fw=new FileWriter(F, true);  
            //�س�������  
            InputStreamReader reader = new InputStreamReader(new FileInputStream(F)); // ����һ������������reader  
            BufferedReader br = new BufferedReader(reader); // ����һ�����������ļ�����ת�ɼ�����ܶ���������  
              
            line = br.readLine();
            int i = 1;
            while (line != null) {  
            	line = br.readLine(); // һ�ζ���һ������ 
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
     * @��ȡϵͳ��ǰʱ�� 
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
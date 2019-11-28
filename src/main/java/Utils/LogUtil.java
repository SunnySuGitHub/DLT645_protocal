package Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.mysql.cj.DataStoreMetadata;
import org.apache.log4j.Logger;

/**
 * 日志工具类
 */
public class LogUtil {
    private static Map<String,Logger> loggerMap = new HashMap<String, Logger>();

    public static void MessageLog(Class clazz,String info){
        String dateString= LocalDateTime.now().getYear()+"年"+LocalDateTime.now().getMonthValue()+"月";
        String parentPath = "log/"+ dateString+"/";
        File parentFile = new File(parentPath);
        if (!parentFile.exists()) parentFile.mkdirs();

        //log4j 调试部分
        Logger infoLog;
        if(loggerMap.containsKey(clazz.getName())){
            infoLog = loggerMap.get(clazz.getName());
        }else{
            infoLog = Logger.getLogger(clazz);//【getLogger(class)的参数用途:追踪产生此日志的类】
        }
        infoLog.debug(info);

        String path = "log/"+dateString+"/"+"dataLog"+LocalDateTime.now().getMonthValue()+"月"+LocalDateTime.now().getDayOfMonth()+"日.txt";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(path,"rw");
            long fileLength = randomAccessFile.length();
            randomAccessFile.seek(fileLength);
            randomAccessFile.writeBytes(LocalDateTime.now().toString()+"\r\n"+info+"\r\n\r\n");
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            try {
                randomAccessFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                randomAccessFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }

    public static void channelLog(String deviceAddress,String info){
        String dateString = LocalDateTime.now().getYear()+"年"+LocalDateTime.now().getMonthValue()+"月";
        String parentPath = "log/"+dateString+"/";
        File parentFile = new File(parentPath);
        if (!parentFile.exists()) parentFile.mkdirs();
        String path = "log/"+dateString+"/"+deviceAddress+"-"+LocalDateTime.now().getMonthValue()+"月"+LocalDateTime.now().getDayOfMonth()+"日";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file,"rw");
            long fileLength = randomAccessFile.length();
            randomAccessFile.seek(fileLength);
            randomAccessFile.writeBytes(LocalDateTime.now().toString()+"\r\n"+info+"\r\n");
            randomAccessFile.close();
        } catch (FileNotFoundException e) {
            try {
                if (randomAccessFile ==null)
                randomAccessFile.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}

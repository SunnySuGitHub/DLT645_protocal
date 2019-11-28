package Test;

import Handler.MessageHandler;
import Handler.SendHelper;
import Utils.ConvertUtil;
import Utils.LogUtil;
import Utils.TimeUtil;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import org.junit.Test;

import Utils.CheckUtil;

import javax.sound.midi.SoundbankResource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;

public class TestDecode {


    @Test
    public void test1() {
        byte[] address = new byte[]{0x12, 0x12, 0x34, 0x37, 0x45, 0x23, 0x67};
        String s = CheckUtil.GetBCDAddress(address);
        System.out.println(s);

    }
    
    @Test
    public void test2(){
        int[] a = new int[0];
        System.out.println(a.length);
    }

    @Test
    public void test3(){
        //发送地址
        String s ="234567891234";
        int a [] = ConvertUtil.addressToBCD(s);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i <a.length ; i++) {
            System.out.print(Integer.toHexString(a[i]));
        }

        System.out.println();
        System.out.println();
        //得到地址
        byte[] b =new byte[7];
        b[0]=0;//因为GetBCDAddress方法是从第一个开始的
        for (int i = 0; i <6 ; i++) {
            b[i+1]= (byte) a[i];
        }
        String str = CheckUtil.GetBCDAddress(b);
        System.out.println(str);
    }

    @Test
    public  void test4(){
        String src ="234567891234";
        char[] c = src.toCharArray();
        int[] dst= new int[6];
        byte byteHigh = 0x00;
        byte byteLow = 0x00;
        int dstLen = src.length()/2; //如果是奇数个，3/2=1
        int srcIndex = 0;
        for (int bytesIndex = 0; bytesIndex < dstLen; ++bytesIndex)
        {
            srcIndex = bytesIndex*2;
            byteHigh = (byte) (c[srcIndex] - '0');
            byteLow = (byte) (c[srcIndex+1] - '0');
            dst[bytesIndex] =  ((byteHigh<<4) | byteLow);
        }
        System.out.println(Arrays.toString(dst));

     }

     @Test
    public void test5(){
         Calendar calendar = Calendar.getInstance();
         int year = calendar.get(Calendar.YEAR);
         System.out.println(year);
         int month = calendar.get(Calendar.MONTH);
         System.out.println(month);
         LocalDateTime localDateTime = LocalDateTime.now();
         System.out.println(localDateTime);
     }

     @Test
    public void test6(){
         String str = TimeUtil.getCurrentTime();
         System.out.println(str);
         String year = str.substring(2,4);
         System.out.println(Integer.valueOf(year));

         String month = str.substring(5,7);
         String day = str.substring(8,10);
         String hour = str.substring(11,13);
         String minutes  = str.substring(14,16);
         String seconds = str.substring(17,19);
         System.out.println(year);
         System.out.println(month);
         System.out.println(day);
         System.out.println(hour);
         System.out.println(minutes);
         System.out.println(seconds);
     }

     @Test
    public void test7(){
         String str = "123456789012";
        int[] a =  ConvertUtil.addressToBCD(str);
         System.out.println(Arrays.toString(a));
     }

     @Test
    public void test8(){
        int a = 15;
         System.out.println(a);
         String c = ConvertUtil.intToHex(a);
         System.out.println(c);
        int ab = 0x81;
         System.out.println(ConvertUtil.intToHex(ab));
     }

     @Test
    public void test9(){
         String controlCode = ConvertUtil.intToHex(129);
         System.out.println(controlCode);
         switch (controlCode){
             case "81": //主站请求读数据应答后 -- 无后续数据帧情况
                 System.out.println("已经收到数据！！！");
                 break;
             case "A1": //主站请求读数据应答后 --> 有后续数据帧情况，需要再次请求后续数据
                 System.out.println("eeeeee");
//                 System.out.println(TimeUtil.getCurrentTime() + " 主站请求读数据后" + "收到有后续数据帧情况 from "
//                         + readData.getDeviceAddress()
//                         + " " + dataTypeString
//                         + " = ");
                 //根据数据标识将获得的数据插入数据库
                 //

                 break;

             default:
                 System.out.println("没有找到！");
                 break;
         }
         System.out.println("可以到这里！");
     }
     @Test
    public void test10(){
         int[] data = new int[]{0x10,0x90,23,34,23,41};
         for (int i = 0; i <data.length ; i++) {
             data[i] = data[i]+0x33;
         }

        byte[] a = new byte[]{(byte)0x10,(byte)0x90};
         System.out.println();

         System.out.println(Arrays.toString(new byte[]{(byte)0x10,(byte)0x90}));
     }

     @Test
    public void test11(){
        int a =0x10;
        int b = 0x90;
         System.out.println(a);
         System.out.println(b);
         System.out.println((byte)a);
         System.out.println((byte)b);
     }


}

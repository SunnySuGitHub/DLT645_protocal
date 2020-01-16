package Test;

import Entity.DataIdentify1997;
import Handler.MessageHandler;
import Handler.SendHelper;
import Params.CommandType;
import Utils.ConvertUtil;
import Utils.LogUtil;
import Utils.TimeUtil;
import org.junit.Test;

import Utils.CheckUtil;

import javax.sound.midi.SoundbankResource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;

public class TestDecode {

    private int low;
    private int high;
    
    @Test
    public void test2(){
        int[] a = new int[0];
        System.out.println(a.length);
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
//                         + readData.getCenterAddress()
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
        int a ='W';
        int b = 'D';
         System.out.println(a);
         System.out.println(b);
         System.out.println((byte)a);
         System.out.println((byte)b);
     }

     @Test
    public void test12(){
        String a = "modify_Comm_Speed";
        System.out.println(a.toUpperCase());
     }

     @Test
    public void test13(){
//        CommandType commandType =CommandType.CUR_NEGATIVE_ACTIVE_POWER;
//        if (commandType.getValue().startsWith("0")) {
//            System.out.println(commandType.getValue());
//            int[] data = CheckUtil.getDataType(commandType);
//            System.out.println(Arrays.toString(data));
//        }
//         System.out.println("*****************");
//         String value = commandType.getValue();
//         System.out.println(value);
//         String substring1 = value.substring(0, 3);
//         String substring2 = value.substring(3, 6);
//         int[] dataType = new int[]{Integer.valueOf(substring1),Integer.valueOf(substring2)};
//         System.out.println(Arrays.toString(dataType));
//
//         byte[] bytes = {(byte) 0x10, (byte) 0x90};
//         System.out.println(Arrays.toString(bytes));


     }

     @Test
    public void test14(){
        int a ='R';
        String b="RRR";
         byte[] bytes = b.getBytes();
         System.out.println(Arrays.toString(bytes));
     }

     @Test
    public void test15(){
        //一个数字分为两个字节，高位在后
        int num=2;
        int offset = 0;
        int[] data=new int[2];
        int high = (num >>> 8);
        int low = num & 0xff;
        data[offset] = low;
        data[offset + 1] = high;
        System.out.println(Arrays.toString(data));
     }

     @Test
    public void test16(){
        ////获得一个整数（不超过两位）的BCD码
        int num =99;
         int i = (((num / 10) << 4) & 0xf0) | ((num % 10) & 0x0f);
         System.out.println(i);
     }

     @Test
    public void test18(){
        String s = "459378000004";
         int[] ints = ConvertUtil.addressToBCD(s);
         System.out.println(Arrays.toString(ints));
         int[] time = TimeUtil.getTime();
         System.out.println(Arrays.toString(time));
     }
}

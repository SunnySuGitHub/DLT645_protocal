package Utils;

import Params.CommandType;

import static java.lang.System.arraycopy;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:48
 */
public class CheckUtil {

    public static String GetCenterAddr(byte[] msg, String dataType) {
        byte[] byteAddress = new byte[4];
        if(dataType.equals("fixed")) {
            arraycopy(msg, 2, byteAddress, 0, 4);
        } else {
            arraycopy(msg, 5, byteAddress, 0, 4);
        }
        String addr = "";
        String EachByteAddress;
        int bitData;
        for(byte addr_b : byteAddress) {
            if (addr_b < 0) {
                bitData = addr_b+256;
            } else {
                bitData = addr_b;
            }
            EachByteAddress = bitData/16 + "" + bitData%16 ;
            addr = EachByteAddress + addr;
        }
        return addr;
    }

    public static int getDataLength(byte[] l) {
        if(l.length != 2) {
            return -1;
        }
        byte low = l[0];
        byte high = l[1];
        high &= 0x03;
        int res = (high&0xff)<<8|(low&0xff);
        return res;
    }

    public static boolean checkData(byte[] data) {
        byte s = 0;
        int j;
        for(j = data.length-1; j > 0; j--) {
            if(data[j]==22) {
                break;
            }
        }
        for(int i=0;i<j-1;i++) {
            s += (data[i] & 0xff) % 256;
        }

        return s == data[j-1];
    }

    //获取数据标识，先低位，后高位
    public static int[] getDataType(CommandType commandType){
        String value = commandType.getValue();
        String DI0 = value.substring(0, 3);
        String DI1 = value.substring(3, 6);
        int[] dataType = new int[]{Integer.valueOf(DI0),Integer.valueOf(DI1)};
        return dataType;
    }

    //根据传输速率修改速率特征字
    public static int getSpeedCharacter(String rate){
        int speed = Integer.parseInt(rate);
        int speedCharacter=0;
        switch (speed){
            case 300:
                speedCharacter = 2;
                break;
            case 600:
                speedCharacter = 4;
                break;
            case 1200:
                speedCharacter = 0;
            case 2400:
                speedCharacter = 16;
                break;
            case 4800:
                speedCharacter = 32;
                break;
            case 9600:
                speedCharacter = 64;
                break;

        }
        return speedCharacter;
    }

    //将从站异常应答转化为错误信息
    public static String getErrorMsg(int a){
        String errorMsg="";
        switch(a){
            case 1:
                errorMsg="非法数据";
                break;
            case 2:
                errorMsg="数据标识错误";
                break;
            case 4:
                errorMsg="密码错误";
                break;
            case 16:
                errorMsg="年时区数超";
                break;
            case 32:
                errorMsg="日时段数超";
                break;
            case 64:
                errorMsg="费率数超";
                break;
        }
        return errorMsg;
    }

    //将从数据库中获得的密码改为可传输的密码
    //写数据时的密码
//    public static int[] getPass(String string){
//        Integer pass = Integer.valueOf(string);
//
//
//    }


}

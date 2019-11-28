package Utils;

import static java.lang.System.arraycopy;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 15:48
 */
public class CheckUtil {

    public static String GetBCDAddress(byte[] address) {
        byte[] byteAddress = new byte[6];
        arraycopy(address, 1, byteAddress, 0, 6);
        String addr = "";
        String EachByteAddress;
        int bitdata;
        for(byte addr_b : byteAddress) {
            if (addr_b < 0) {
                bitdata = addr_b+256;
            } else {
                bitdata = addr_b;
            }
            EachByteAddress = bitdata/16 + "" + bitdata%16 ;
            addr = EachByteAddress + addr;
        }
        return addr;
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

    //整理数据标识，先低位，后高位
    public static int[] getDataType(String string){
        int[] dataType = new int[2];
        //...
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

}

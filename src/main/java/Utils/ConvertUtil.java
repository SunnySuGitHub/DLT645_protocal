package Utils;

import java.util.Arrays;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 16:23
 */
public class ConvertUtil {
    static char[] BCDCode=new char[]{'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','0'};
    public static String ToHex(byte[] bytes) {
        int[] read_ints = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            if ((int) bytes[i] > 0) {
                read_ints[i] = (int) bytes[i];
            } else {
                read_ints[i] = (int) bytes[i] + 256;
            }
        }
        String str_addr = "";
        String EachByteAddress;
        for (int addr_b : read_ints) {
            EachByteAddress = BCDCode[addr_b / 16]+""+ BCDCode[addr_b % 16] +" ";
            str_addr =str_addr + EachByteAddress;
        }
        return "[ "+str_addr+" ]";
    }

    public static int[] bytesToInts(byte[] bytes){
        if(bytes == null || bytes.length == 0) return null;
        int[] data = new int[bytes.length];
        for(int i = 0 ; i < bytes.length; i++){
            data[i] = bytes[i] & 0xff;
        }
        return data;
    }

    public static String intToHex(int n) {
        if(n == 0) return "0";
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        while(n != 0){
            s = s.append(b[n%16]);
            n = n/16;
        }
        a = s.reverse().toString();
        return a;
    }
}

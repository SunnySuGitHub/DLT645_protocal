package Utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/12/20 15:17
 */
public class CheckUtilTest {

    @Test
    public void getCenterAddr() {
        byte[] msg = new byte[]{0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x13, 0x13};
        String res = CheckUtil.GetCenterAddr(msg, "fixed");
        System.out.println(res);
    }
}
package Utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/15 16:15
 */
public class TimeUtil {

    public static String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String d1 = format.format(now);
        return d1;
    }

    public static String getYesterday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        return yesterday;
    }

    public static String getToday() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
        Date now = new Date();
        String d1 = format1.format(now);
        return d1;
    }

    public static String getCurrentMonth() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        String curMonth = format.format(now);
        return curMonth;
    }

    public static String getLastMonth() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyyMM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return format1.format((cal.getTime()));
    }

    public static int[] getTime(){

        String str = TimeUtil.getCurrentTime();
        int[] time = new int[]{Integer.valueOf(str.substring(17,19)),Integer.valueOf(str.substring(14,16)),
                Integer.valueOf(str.substring(11,13)),Integer.valueOf(str.substring(8,10)),
                Integer.valueOf(str.substring(5,7)),Integer.valueOf(str.substring(2,4))};
        return time;

    }

}

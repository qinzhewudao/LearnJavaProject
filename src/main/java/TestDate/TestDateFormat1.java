package TestDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author sheyang
 * created at 2018/7/30
 */
public class TestDateFormat1 {
    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy年MM月dd日hh时mm分ss秒SS毫秒,属于本年的第w周,本月的第W周");

        Date d = new Date(213131231111L);
        String str = df.format(d);

        System.out.println(str);

        System.out.println("###############################");

        String str2 = "1988-7-7";
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d2 = df2.parse(str2);
            System.out.println(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

package TestDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * author sheyang
 * created at 2018/8/20
 */
public class TestDataFormat2 {

    public static void main(String[] args) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式


        String hehe = dateFormat.format(now);
        System.out.println(hehe);

        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

        c.setTime(now);

        now = c.getTime();//例如：2017-05-26
        System.out.println(now);


        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        System.out.println(year + "/" + month + "/" + date + " " + hour + ":" + minute + ":" + second);


        //获取上一天时间：

        //日期格式化
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //字符串转日期
        Date fileDate = null;

        Calendar calendar = Calendar.getInstance();

        //获取fileDate上一天
        calendar.add(Calendar.DAY_OF_MONTH, -1);

        //获取后一天

        calendar.add(Calendar.DAY_OF_MONTH, 1);

        fileDate = calendar.getTime();
        //日期转字符串


        //获取各月最后一天时间：

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");
        for (int i = 0; i < 12; i++) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(Calendar.YEAR, 2017);
            calendar2.set(Calendar.MONTH, i);
            calendar2.set(Calendar.DATE, 1);
            int lastDay = calendar2.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar2.set(Calendar.DAY_OF_MONTH, lastDay);
            String lastDayOfMonth = sdf3.format(calendar2.getTime());
            System.out.println(lastDayOfMonth);
        }


        //注意：月份从0开始到11表示1到12月份,Date跟Calendar获取的时间都是当前服务器所在的计算机的系统时间！
    }


}



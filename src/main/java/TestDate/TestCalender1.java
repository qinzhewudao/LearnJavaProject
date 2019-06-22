package TestDate;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * author sheyang
 * created at 2018/7/30
 */
public class TestCalender1 {
    public static void main(String[] args){
        Calendar ca = new GregorianCalendar();
        ca.set(2011,2,3,12,25,45);
        Date d = ca.getTime();
        System.out.println(d);
    }
    
}

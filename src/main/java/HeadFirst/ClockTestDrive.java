package HeadFirst;

/**
 * author sheyang
 * created at 2018/10/24
 */
class Clock {

    String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

public class ClockTestDrive {
    public static void main(String[] args) {
        Clock c = new Clock();
        c.setTime("1245");

        String tod = c.getTime();
        System.out.println("time: " + tod);
    }
}

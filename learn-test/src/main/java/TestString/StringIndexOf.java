package TestString;

import java.util.StringTokenizer;

/**
 * @author sheyang
 * @date 2021/8/25 10:09 上午
 */
public class StringIndexOf {

    public static void main(String[] args) {
        String origin = "0123:567sheyangHaHaHa";
        int circleCount = 10000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < circleCount; i++) {
            int index = origin.indexOf(":");
            String place = origin.substring(0, index);
//            System.out.println(place);
        }
        System.out.println(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < circleCount; i++) {
            String place = origin.split(":")[0];
//            System.out.println(place);
        }
        System.out.println(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < circleCount; i++) {
            StringTokenizer st = new StringTokenizer(origin, ":");
            String place = st.nextToken();
//            System.out.println(place);
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

}

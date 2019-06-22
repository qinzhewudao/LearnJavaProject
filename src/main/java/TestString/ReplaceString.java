package TestString;

import java.util.Scanner;

/**
 * created at 2019/5/18
 *
 * @author sheyang
 */
public class ReplaceString {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        String[] arr = new String[num];
        for (int i = 0; i < num; i++) {
            StringBuilder sb = new StringBuilder(scan.next());
            for (int j = 0; j < sb.length(); ) {
                if (j + 2 < sb.length() && sb.charAt(j) == sb.charAt(j + 1) && sb.charAt(j + 1) == sb.charAt(j + 2)) {
                    sb.deleteCharAt(j);
                } else if (j + 3 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)
                        && sb.charAt(j + 2) == sb.charAt(j + 3)) {
                    sb.deleteCharAt(j + 2);
                } else {
                    j++;
                }
            }
            arr[i] = sb.toString();
        }
        for (int i = 0; i < num; i++) {
            System.out.println(arr[i]);
        }
    }

}

package TestIO;

import java.io.Console;
import java.util.Scanner;

/**
 * author sheyang
 * created at 2018/9/3
 */
public class TestScanner {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Input name :");
//读一行
        String name = in.nextLine();
//读一个单词，空格分隔
        String name2 = in.next();
//读整数
        if(in.hasNextInt()){
            int age = in.nextInt();
        }
//读取密码不用Scanner类，可以用Console类
        Console terminal = System.console();
        String userNm = terminal.readLine("User name: ");
        char[] pwd = terminal.readPassword("Password: ");
        System.out.println(userNm);
        System.out.println(pwd);
//密码用char[]比String安全，用完后可以重写char[]!!!!!
    }

}

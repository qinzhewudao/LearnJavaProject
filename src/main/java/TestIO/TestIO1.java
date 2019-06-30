package TestIO;

import java.io.*;

/**
 * author sheyang
 * created at 2018/8/7
 */
public class TestIO1 {

    public static void main(String[] args) throws IOException {
        File file = new File("hello.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try {
            BufferedReader br =
                    new BufferedReader(new FileReader("file.txt"));

            String line = br.readLine();

            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
package asm;

/**
 * @author sheyang
 * @date 2021/7/1 2:08 下午
 */
public class HelloWorld {

    public void sayHello() {
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
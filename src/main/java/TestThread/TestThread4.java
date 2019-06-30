package TestThread;

/**
 * author sheyang
 * created at 2018/7/28
 * Thread的几种简单方法的测试
 */
public class TestThread4 {
    public static void main(String[] args) {
        TestThread4 testThread4 = new TestThread4();
        MyThread thread = testThread4.new MyThread();
        thread.start();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {

        }
        thread.interrupt();
    }

    class MyThread extends Thread {
//        @Override
//        //打断处于阻塞的线程
//        public void run() {
//
//            try{
//                System.out.println("进入睡眠状态");
//                Thread.currentThread().sleep(10000);
//                System.out.println("睡眠完毕");
//            }catch(InterruptedException e){
//                System.out.println("得到中断异常");
//            }
//            System.out.println("run方法执行完毕");
//        }

//        @Override
//        //interrupt打不断正在运行的线程
//        public void run() {
//            int i = 0;
//            while(i<100000){
//                System.out.println(i+" while循环");
//                i++;
//            }
//        }

        @Override
        //通过isInterrupted()来判断是得到了被打断的请求
        //不过一般不通过这种方式来获取推荐使用自己添加的标志位isStop再在外面通过set方法来设置这个标志位来结束循环
        public void run() {
            int i = 0;
            while (!isInterrupted() && i < Integer.MAX_VALUE) {
                System.out.println(i + " while循环");
                i++;
            }

        }

    }
}

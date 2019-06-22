package TestThread;

/**
 * author sheyang
 * created at 2018/7/27
 */
public class TestThread1 {
    public static void main(String[] args)  {
        System.out.println("主线程ID:"+Thread.currentThread().getId());
        MyThread thread1 = new MyThread("调用start方法生成的thread1");
        thread1.start();
        MyThread thread2 = new MyThread("调用run方法生成的thread2");
        //调用run方法其实还是在主线程，并没有新建线程，必须使用start方法
        thread2.run();

        //线程的第二种创建方法
        System.out.println("主线程ID："+Thread.currentThread().getId());
        MyRunnable runnable = new MyRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
        //调用自身的run方法
        runnable.run();

        //简便写法

           new Thread(){
               public void run() {
                  // fun1();
               }
           }.start();

//
//        ProcessBuilder processBuilder = new ProcessBuilder("bin/sh","/c","ipconfig/all");
//        try {
//            Process process = processBuilder.start();
//            Scanner scanner = new Scanner(process.getInputStream());
//            while(scanner.hasNextLine()){
//                System.out.println(scanner.nextLine());
//            }
//            scanner.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//


    }
}


class MyThread extends Thread{
    private String name;

    public MyThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("name:"+name+" 子线程ID:"+Thread.currentThread().getId());
    }
}


class MyRunnable implements Runnable{

    public MyRunnable() {

    }

    @Override
    public void run() {
        System.out.println("实现Runable接口生成的子线程ID："+Thread.currentThread().getId());
    }
}
package HeadFirst;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * author sheyang
 * created at 2018/10/15
 */
public class GumballMachineTestDrive {

    /**
     * 输出结果:
     * 糖果机位置: 东直门
     * 目前库存 : 500
     * 当前状态 : 售卖中...
     * @param args
     */
    public static void main(String[] args) throws MalformedURLException, NotBoundException {
        //创建一个机器的数组,条件有限,就写一个吧,假装他是多个,,,
        String[] location = {"rmi://192.168.31.149/gumballMachine"};
        //创建多个监视器
        GumballMonitor[] monitor = new GumballMonitor[location.length];
        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachine gumballMachine = new GumballMachine("近铁",500);
                monitor[i] = new GumballMonitor(gumballMachine);
                System.out.println(monitor[i]);

                monitor[i].report();

                gumballMachine.setInventory(400);

                monitor[i].report();

            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }
    }
}

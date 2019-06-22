package HeadFirst;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 必须继承Remote接口,提供一组可以远程调用的方法
 * 确定所有方法的返回值,都是可序列化的
 * author sheyang
 * created at 2018/10/16
 */
public interface GumballMachineRemote extends Remote {

    int getCount() throws RemoteException;

    String getLocation() throws RemoteException;

    State getState() throws RemoteException;
}

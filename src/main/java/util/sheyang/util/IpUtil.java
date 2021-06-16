package util.sheyang.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * ip 工具类
 */
public class IpUtil {

    /**
     * 本机ip
     */
    public static String LOCAL_IP;

    static {
        LOCAL_IP = getLocalHostExactAddress();
    }

    public static String getLocalHostExactAddress() {
        try {
            String localIp = null;

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface netFace = networkInterfaces.nextElement();
                // 该网卡接口下的ip会有多个，也需要一个个的遍历，找到自己所需要的
                for (Enumeration<InetAddress> inetAddrEnum = netFace.getInetAddresses(); inetAddrEnum.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddrEnum.nextElement();
                    // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                    if (inetAddr.isLoopbackAddress() || inetAddr.getHostAddress().contains(":")) {
                        continue;
                    }
                    if (inetAddr.isSiteLocalAddress()) {
                        // 如果是site-local地址，就是它了 就是我们要找的
                        // ~~~~~~~~~~~~~绝大部分情况下都会在此处返回你的ip地址值~~~~~~~~~~~~~
                        return inetAddr.getHostAddress();
                    }
                    // 若不是site-local地址 那就记录下该地址当作候选
                    if (localIp == null) {
                        localIp = inetAddr.getHostAddress();
                    }
                }
            }
            // 如果出去loopback回环地之外无其它地址了，那就回退到原始方案吧
            return localIp == null ? InetAddress.getLocalHost().getHostAddress() : localIp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("获取本地Ip失败");
    }

    public static void main(String[] args) {
        System.out.println(LOCAL_IP);
    }

}

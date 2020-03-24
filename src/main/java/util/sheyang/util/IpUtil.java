package util.sheyang.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * ip 工具类
 */
public class IpUtil {

    private static final Logger log = LoggerFactory.getLogger(IpUtil.class);

    /**
     * 本机ip
     */
    public static String LOCAL_IP;

    static {
        LOCAL_IP = getLocalIp();
    }

    /**
     * 获取本机ip
     *
     * @return 本机ip
     */
    public static String getLocalIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("getLocalIp error", e);
        }

        return "";
    }

}

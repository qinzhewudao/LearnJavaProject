package util;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author: sheyang
 * @date: 2020/2/15 5:22 下午
 */
@Slf4j
public class SerializeUtil {

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        ByteArrayOutputStream baos;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (Exception e) {
            log.error("object :{} serialize fail", object, e);
        }
        return null;
    }

    public static Object unSerialize(byte[] bytes) {
        ByteArrayInputStream bais;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            log.error("bytes :{} unSerialize fail", bytes, e);
        }
        return null;
    }


}

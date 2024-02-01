//package util.sheyang.util;
//
//import com.yupaopao.platform.game.undercover.module.GameConfig;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * @author: sheyang
// * @date: 2020/3/7 10:47 上午
// */
//public class RoleUtils {
//
//    public static List<Byte> getRandomRole(int numbers, List<GameConfig.RoleUnit> roleUnits) {
//        List<Byte> result = new ArrayList<>();
//
//        GameConfig.RoleUnit unClearUnit = null;
//        for (GameConfig.RoleUnit roleUnit : roleUnits) {
//            if (roleUnit.getRoleSize() < 0) {
//                unClearUnit = roleUnit;
//                continue;
//            }
//            for (int i = 0; i < roleUnit.getRoleSize(); i++) {
//                result.add(roleUnit.getRoleType());
//            }
//        }
//        if (null != unClearUnit) {
//            int limit = numbers - result.size();
//            for (int i = 0; i < limit; i++) {
//                result.add(unClearUnit.getRoleType());
//            }
//        }
//
//        Collections.shuffle(result);
//
//        return result;
//    }
//
//}

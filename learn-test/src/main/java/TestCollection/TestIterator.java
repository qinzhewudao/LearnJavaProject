package TestCollection;

import java.util.*;

/**
 * author sheyang
 * created at 2018/8/27
 */
public class TestIterator {
    public static void main(String[] args) {
        List<String> aList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            aList.add("a" + i);
        }
        System.out.println(aList);
        for (Iterator<String> item = aList.iterator(); item.hasNext(); ) {
            String temp = item.next();
            System.out.print(temp + "\t");
            // 删除3结尾的字符串
            if (temp.endsWith("3")) {
                item.remove();
            }
        }
        System.out.println();
        System.out.println(aList);

        Set<String> set = new HashSet<String>();
        for (int i = 0; i < 5; i++) {
            set.add("a" + i);
        }
        System.out.println(set);
        for (String temp : set) {
            System.out.print(temp + "\t");
        }
        System.out.println();
        System.out.println(set);

        Map<String, String> map = new HashMap<>();
        map.put("A", "高淇");
        map.put("B", "高小七");
        Set<Map.Entry<String, String>> ss = map.entrySet();
        for (Iterator<Map.Entry<String, String>> iterator = ss.iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> e = iterator.next();
            System.out.println(e.getKey() + "--" + e.getValue());
        }

        Map<String, String> map2 = new HashMap<String, String>();
        map.put("A", "高淇");
        map.put("B", "高小七");
        Set<String> ss2 = map2.keySet();
        for (Iterator<String> iterator = ss2.iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            System.out.println(key + "--" + map2.get(key));
        }

        System.out.println("-----------------------------");

        List<String> aList2 = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            aList2.add("a" + i);
        }
        System.out.println(aList2);
        Collections.shuffle(aList2); // 随机排列
        System.out.println(aList2);
        Collections.reverse(aList2); // 逆续
        System.out.println(aList2);
        Collections.sort(aList2); // 排序
        System.out.println(aList2);
        System.out.println(Collections.binarySearch(aList2, "a2"));
        Collections.fill(aList2, "hello");
        System.out.println(aList2);
    }
}

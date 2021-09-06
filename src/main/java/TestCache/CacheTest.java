package TestCache;

/**
 * @author sheyang
 * @date 2021/7/12 5:09 下午
 */
public class CacheTest {

    public static void main(String[] args) {
        String key = "sheyang";
        LRUCache<String, String> lruCache = new LRUCache<>();
        lruCache.put(key, "first");
        System.out.println(lruCache.get(key));
        LFUCache<String, String> lfuCache = new LFUCache<>();
        lfuCache.put(key, "first");
        System.out.println(lfuCache.get("sheyang"));
    }

}

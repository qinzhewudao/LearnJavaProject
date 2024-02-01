package algorithmn;

import java.util.HashMap;

class lruNode {
    int key;
    int value;
    lruNode next;
    lruNode pre;

    public lruNode(int key, int value, lruNode pre, lruNode next) {
        this.key = key;
        this.value = value;
        this.pre = pre;
        this.next = next;
    }
}

public class LRUCache {
    int capacity;
    //cache size
    int count;
    lruNode head;
    lruNode tail;
    HashMap<Integer, lruNode> hm;

    public LRUCache(int capacity) { //only initial 2 Node is enough, head/tail
        this.capacity = capacity;
        this.count = 2;
        this.head = new lruNode(-1, -1, null, null);
        this.tail = new lruNode(-2, -2, this.head, null);
        this.head.next = this.tail;
        hm = new HashMap<Integer, lruNode>();
        hm.put(this.head.key, this.head);
        hm.put(this.tail.key, this.tail);
    }

    public int get(int key) {
        int value = -1;
        if (hm.containsKey(key)) {
            lruNode nd = hm.get(key);
            value = nd.value;
            //detach nd from current place
            detachNode(nd);
            //insert nd into head
            insertToHead(nd);
        }
        return value;
    }

    public void put(int key, int value) {
        //update
        if (hm.containsKey(key)) {
            lruNode nd = hm.get(key);
            nd.value = value;
            //move to head
            //detach nd from current place
            detachNode(nd);
            //insert nd into head
            insertToHead(nd);

        } else { //add
            lruNode newNd = new lruNode(key, value, null, this.head);
            this.head.pre = newNd; //insert into head
            this.head = newNd;
            hm.put(key, newNd); //add into hashMap
            this.count++;
            if (this.count > capacity) { //need delete node
                removeNode();
            }
        }
    }

    /**
     * 插入节点到头部
     *
     * @param nd
     */
    public void insertToHead(lruNode nd) {
        this.head.pre = nd;
        nd.next = this.head;
        nd.pre = null;
        this.head = nd;
    }

    /**
     * 删除节点
     *
     * @param nd
     */
    public void detachNode(lruNode nd) {
        nd.pre.next = nd.next;
        if (nd.next != null) {
            nd.next.pre = nd.pre;
        } else {
            this.tail = nd.pre;
        }
    }

    public void removeNode() { //remove from tail
        int tailKey = this.tail.key;
        this.tail = this.tail.pre;
        this.tail.next = null;
        hm.remove(tailKey);
        this.count--;
    }

    public void printCache() {
        System.out.println("\nPRINT CACHE ------ ");
        System.out.println("count: " + count);
        System.out.println("From head:");
        lruNode p = this.head;
        while (p != null) {
            System.out.println("key: " + p.key + " value: " + p.value);
            p = p.next;
        }
        System.out.println("From tail:");
        p = this.tail;
        while (p != null) {
            System.out.println("key: " + p.key + " value: " + p.value);
            p = p.pre;
        }

    }

    public static void main(String[] args) {
        LRUCache lc = new LRUCache(3);
        lc.printCache();

        lc.put(1, 1);
        lc.put(2, 2);
        lc.put(3, 3);
        lc.printCache();

        lc.get(2);
        lc.printCache();

        lc.put(4, 4);
        lc.printCache();

        lc.get(1);
        lc.printCache();

        lc.put(3, 33);
        lc.printCache();
    }
}

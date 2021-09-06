/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package TestCache;

import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LRU-2
 * LRU-K
 * <p>
 * LRU-K 中的 K 代表最近使用的次数，因此 LRU 可以认为是 LRU-1。LRU-K 的主要目的是为了解决 LRU 算法“缓存污染”的问题，其核心思想是将“最近使用过 1 次”的判断标准扩展为“最近使用过 K 次”。
 * <p>
 * LRU-K 需要多维护一个队列，用于记录所有缓存数据被访问的历史。只有当数据的访问次数达到 K 次的时候，才将数据放入缓存。下一次访问数据，同样先加入到历史队列，再次达到 K 次时，把它从历史队列删除，同时调整在缓存队列中的位置。也就是说，访问 K 次调整一次在缓存队列中的位置。
 * <p>
 * 缓存中的数据遵循 LRU 的规则，即在需要淘汰时，选择第 K 次访问时间距当前时间最大的数据淘汰。
 * <p>
 * 实际应用中 LRU-2 是综合各种因素后最优的选择，LRU-3 或者更大的 K 值命中率会高，但适应性差，需要大量的数据访问才能将历史访问记录清除掉。
 * <p>
 * 下面借用 LinkedHashMap 实现 LRU2 算法。
 * ————————————————
 * 版权声明：本文为CSDN博主「Augustvic」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/Victorgcx/article/details/104378378
 * </p>
 * When the data accessed for the first time, add it to history list. If the size of history list reaches max capacity, eliminate the earliest data (first in first out).
 * When the data already exists in the history list, and be accessed for the second time, then it will be put into cache.
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = -5167631809472116969L;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static final int DEFAULT_MAX_CAPACITY = 1000;
    private final Lock lock = new ReentrantLock();
    private volatile int maxCapacity;

    // as history list
    private PreCache<K, Boolean> preCache;

    public LRUCache() {
        this(DEFAULT_MAX_CAPACITY);
    }

    public LRUCache(int maxCapacity) {
        super(16, DEFAULT_LOAD_FACTOR, true);
        this.maxCapacity = maxCapacity;
        this.preCache = new PreCache<>(maxCapacity);
    }

    @Override
    protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
        return size() > maxCapacity;
    }

    @Override
    public boolean containsKey(Object key) {
        lock.lock();
        try {
            return super.containsKey(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V get(Object key) {
        lock.lock();
        try {
            return super.get(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V put(K key, V value) {
        lock.lock();
        try {
            if (preCache.containsKey(key)) {
                // add it to cache
                preCache.remove(key);
                return super.put(key, value);
            } else {
                // add it to history list
                preCache.put(key, true);
                return value;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public V remove(Object key) {
        lock.lock();
        try {
            preCache.remove(key);
            return super.remove(key);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int size() {
        lock.lock();
        try {
            return super.size();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void clear() {
        lock.lock();
        try {
            preCache.clear();
            super.clear();
        } finally {
            lock.unlock();
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        preCache.setMaxCapacity(maxCapacity);
        this.maxCapacity = maxCapacity;
    }

    static class PreCache<K, V> extends LinkedHashMap<K, V> {

        private volatile int maxCapacity;

        public PreCache() {
            this(DEFAULT_MAX_CAPACITY);
        }

        public PreCache(int maxCapacity) {
            super(16, DEFAULT_LOAD_FACTOR, true);
            this.maxCapacity = maxCapacity;
        }

        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
            return size() > maxCapacity;
        }

        public void setMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
        }
    }

}
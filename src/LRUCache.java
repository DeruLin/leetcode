import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {

    private int capacity;
    private MyLRUCache<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new MyLRUCache<>(capacity);
    }

    public int get(int key) {
        if(cache.containsKey(key))
            return cache.get(key);
        return -1;
    }

    public void put(int key, int val) {
        cache.put(key, val);
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        int param_1 = obj.get(1);
        obj.put(1, 2);
    }

}

class MyLRUCache<K, V> extends LinkedHashMap<K, V> {

    private int capacity;

    public MyLRUCache(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
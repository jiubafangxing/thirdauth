package www.thirdauth.com.thirdparty;

public interface CacheAble<K,V> {

    void put(K key, V value, long ttlMillis);

    V get(K key);

}

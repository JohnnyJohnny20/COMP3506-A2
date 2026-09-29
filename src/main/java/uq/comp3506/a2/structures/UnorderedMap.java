// @edu:student-assignment

package uq.comp3506.a2.structures;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 * <p>
 * NOTE: You should go and carefully read the documentation provided in the
 * MapInterface.java file - this explains some of the required functionality.
 */
public class UnorderedMap<K, V> implements MapInterface<K, V> {

    private static final int START_CAPACITY = 11;
    /**
     * you will need to put some member variables here to track your
     * data, size, capacity, etc...
     */
    private int bucketCount = START_CAPACITY;
    private int size = 0; // pair count
    private List<LinkedList<Entry<K, V>>> data;

    private void initBuckets(int capacity) {
        this.data = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            this.data.add(null);
        }
    }

    /**
     * Constructs an empty UnorderedMap
     */
    public UnorderedMap() {
        // Implement me!
        initBuckets(START_CAPACITY);
    }

    /**
     * returns the size of the structure in terms of pairs
     * @return the number of kv pairs stored
     */
    @Override
    public int size() {
        // Implement me!
        return this.size;
    }

    /**
     * helper to indicate if the structure is empty or not
     * @return true if the map contains no key-value pairs, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Clears all elements from the map. That means, after calling clear(),
     * the return of size() should be 0, and the data structure should appear
     * to be "empty".
     */
    @Override
    public void clear() {
        // Implement me!
        this.size = 0;
        this.bucketCount = START_CAPACITY;
        initBuckets(START_CAPACITY);
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value
     * is replaced by the specified value.
     *
     * @param key   the key with which the specified value is to be associated
     * @param value the payload data value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no such key
     */
    @Override
    public V put(K key, V value) {
        // Implement me!
        int bucketIdx = getBucketIdx(key);

        LinkedList<Entry<K, V>> bucket = this.data.get(bucketIdx);
        V prevValue = null;
        if (bucket == null) {
            bucket = new LinkedList<>();
            this.data.set(bucketIdx, bucket);
        } else {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    prevValue = entry.getValue();
                    entry.setValue(value);
                    return prevValue;
                }
            }
        }
        bucket.add(new Entry<>(key, value));
        this.size++;

        if ((double) this.size / this.bucketCount > 0.8) {
            resize();
        }

        return prevValue;
    }

    /**
     * Looks up the specified key in this map, returning its associated value
     * if such key exists.
     *
     * @param key the key with which the specified value is to be associated
     * @return the value associated with key, or null if there was no such key
     */
    @Override
    public V get(K key) {
        // Implement me!
        int bucketIdx = getBucketIdx(key);
        LinkedList<Entry<K, V>> bucket = this.data.get(bucketIdx);
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    /**
     * Looks up the specified key in this map, and removes the key-value pair
     * if the key exists.
     *
     * @param key the key with which the specified value is to be associated
     * @return the value associated with key, or null if there was no such key
     */
    @Override
    public V remove(K key) {
        // Implement me!
        int bucketIdx = getBucketIdx(key);
        LinkedList<Entry<K, V>> bucket = this.data.get(bucketIdx);
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V remValue = entry.getValue();
                    bucket.remove(entry);
                    this.size--;
                    return remValue;
                }
            }
        }
        return null;
    }

    // More helpers here if you need 

    private int getBucketIdx(K key) {
        int bucketIdx = key.hashCode() % this.bucketCount;
        if (bucketIdx < 0) {
            bucketIdx += this.bucketCount;
        }
        return bucketIdx;
    }

    // Claude assisted prime functions
    private static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private static int nextPrime(int from) {
        int candidate = from;
        while (!isPrime(candidate)) {
            candidate++;
        }
        return candidate;
    }

    private void rehashInsert(K key, V value) {
        int bucketIdx = getBucketIdx(key);
        LinkedList<Entry<K, V>> bucket = this.data.get(bucketIdx);
        if (bucket == null) {
            bucket = new LinkedList<>();
            this.data.set(bucketIdx, bucket);
        }
        bucket.add(new Entry<>(key, value));
    }

    private void resize() {
        List<LinkedList<Entry<K, V>>> oldData = this.data;
        int newCapacity = nextPrime(this.bucketCount * 2);
        initBuckets(newCapacity);
        this.bucketCount = newCapacity;

        for (LinkedList<Entry<K, V>> bucket : oldData) {
            if (bucket == null) {
                continue;
            }
            for (Entry<K, V> entry : bucket) {
                rehashInsert(entry.getKey(), entry.getValue());
            }
        }
    }
}

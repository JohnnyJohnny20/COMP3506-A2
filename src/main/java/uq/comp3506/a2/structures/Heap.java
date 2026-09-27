// @edu:student-assignment

package uq.comp3506.a2.structures;

import java.util.List;
import java.util.ArrayList;

/**
 * Supplied by the COMP3506/7505 teaching team, Semester 2, 2026.
 * Implements an unbounded size min-heap (we will assume min-heap
 * semantics, meaning that smaller keys have higher priority).
 */
public class Heap<K extends Comparable<K>, V> {

    /**
     * size tracks the total number of elements in the heap.
     * you could just use data.size() instead if you like...
     */
    private int size = 0;

    /**
     * data stores the raw Entry objects and can grow indefinitely
     */
    private List<Entry<K, V>> data;

    /**
     * Constructs an empty heap with the default constructor
     */
    public Heap() {
        this.data = new ArrayList<>();
        // Implement me!
    }

    /**
     * Constructs a heap via in-place bottom-up construction by taking an
     * ArrayList of Entry types and converting them into a heap.
     * This task is for **COMP7505** students only.
     * This should run in O(n) time with O(1) additional space usage.
     */
    public Heap(ArrayList<Entry<K, V>> arr) {
        // Implement me!
        // Ignore if you are COMP3506
    }

    /**
     * Returns the index of the parent of the node at index i
     */
    private int parent(int i) { 
        // Implement me!
        return (i - 1) / 2;
    }

    /**
     * Returns the index of the left child of the node at index i
     */
    private int left(int i) { 
        // Implement me!
        return 2 * i + 1;
    }

    /**
     * Returns the index of the right child of the node at index i
     */
    private int right(int i) { 
        // Implement me!
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Entry<K, V> temp = this.data.get(i);
        this.data.set(i, this.data.get(j));
        this.data.set(j, temp);
    }

    /**
     * Swaps the node at index i upwards until the heap property is satisfied
     */
    private void upHeap(int i) {
        if (i == 0) {
            return;
        }
        int p = parent(i);
        if (this.data.get(i).getKey().compareTo(this.data.get(p).getKey()) < 0) {
            swap(i, p);
            upHeap(p);
        }
    }

    /**
     * Swaps the node at index i downwards until the heap property is satisfied
     */
    private void downHeap(int i) {
        if (i >= this.size) {
            return;
        }
        int smallest = i;
        K currentKey = this.data.get(smallest).getKey();
        int leftIdx = left(i);

        if (leftIdx < this.size && this.data.get(leftIdx).getKey().compareTo(currentKey) < 0) {
            smallest = leftIdx;
        }

        int rightIdx = right(i);
        currentKey = this.data.get(smallest).getKey();
        if (rightIdx < this.size && this.data.get(rightIdx).getKey().compareTo(currentKey) < 0) {
            smallest = rightIdx;
        }

        if (smallest != i) {
            swap(smallest, i);
            downHeap(smallest);
        }

    }

    /** The number of elements in the heap*/
    public int size() {
        // Implement me!
        return this.size;
    }

    /** True if there are no elements in the heap; false otherwise*/
    public boolean isEmpty() {
        // Implement me!
        return this.size == 0;
    }

    /**
     * Add a key/value pair (an Entry) to the heap.
     * Time complexity for full marks: O(log n)*
     * Amortized because the array may resize.
     */
    public void insert(K key, V value) {
        // Implement me!
        Entry<K, V> entry = new Entry<>(key, value);
        insert(entry);
    }

    /**
     * Add a key/value pair contained in an Entry to the heap.
     * This is just a helper for the above insert, or vice versa.
     * Time complexity for full marks: O(log n)*
     */
    public void insert(Entry<K, V> entry) {
        // Implement me!
        this.data.add(entry);
        upHeap(this.size);
        this.size++;
   
    }

    /**
     * We assume smaller keys have higher priority, so this method will
     * remove and return the highest priority element from the heap.
     * Time complexity for full marks: O(log n)
     * @return the Entry at the top of the heap
     * Note: Return null if empty.
     */
    public Entry<K, V> removeMin() {
        if (this.isEmpty()) {
            return null;
        }
        Entry<K, V> root = this.data.getFirst();
        swap(0, this.size - 1);
        this.data.remove(this.size - 1); // So this.size == data.size()
        this.size--;
        downHeap(0);
        return root;
    }

    /**
     * We assume smaller keys have higher priority, so this method will
     * return a copy of the highest priority element in the heap, but it
     * wont remove it.
     * Time complexity for full marks: O(1)
     * @return the Entry at the top of the heap
     * Note: Return null if empty
     */
    public Entry<K, V> peekMin() {
        if (this.isEmpty()) {
            return null;
        }
        return new Entry<>(this.data.getFirst().getKey(), this.data.getFirst().getValue());
    }

    /**
     * Sort all of the elements inside the heap, in-place.
     * Since we are using a min-heap, this means the largest element
     * will end up at index 0, the smallest at index n-1.
     * Time complexity for full marks: O(n log n), with O(1) additional
     * space being consumed.
     * **COMP7505** only
     */
    public void sortInPlace() {
        // Implement me!
        // Ignore if you are COMP3506
    }

    /**
     * Clear all of the data and reset the heap to an empty state/
     */
    public void clear() {
        // Implement me!
        this.data.clear();
        this.size = 0;
    }

}

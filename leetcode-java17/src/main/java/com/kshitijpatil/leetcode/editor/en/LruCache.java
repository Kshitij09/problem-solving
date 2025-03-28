//Design a data structure that follows the constraints of a Least Recently Used 
//(LRU) cache. 
//
// Implement the LRUCache class: 
//
// 
// LRUCache(int capacity) Initialize the LRU cache with positive size capacity. 
//
// int get(int key) Return the value of the key if the key exists, otherwise 
//return -1. 
// void put(int key, int value) Update the value of the key if the key exists. 
//Otherwise, add the key-value pair to the cache. If the number of keys exceeds 
//the capacity from this operation, evict the least recently used key. 
// 
//
// The functions get and put must each run in O(1) average time complexity. 
//
// 
// Example 1: 
//
// 
//Input
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//Output
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//Explanation
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // cache is {1=1}
//lRUCache.put(2, 2); // cache is {1=1, 2=2}
//lRUCache.get(1);    // return 1
//lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//lRUCache.get(2);    // returns -1 (not found)
//lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//lRUCache.get(1);    // return -1 (not found)
//lRUCache.get(3);    // return 3
//lRUCache.get(4);    // return 4
// 
//
// 
// Constraints: 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10⁴ 
// 0 <= value <= 10⁵ 
// At most 2 * 10⁵ calls will be made to get and put. 
// 
//
// Related Topics Hash Table Linked List Design Doubly-Linked List 👍 21615 👎 1
//121

package com.kshitijpatil.leetcode.editor.en;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    public static void main(String[] args) {
        LRUCache solution = new LruCache().new LRUCache(5);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class LRUCache {
    private final DoublyLinkedList<Integer> accessHistory;
    private final Map<Integer, DoublyLinkedList.Entry<Integer>> lookup;
    int maxCapacity;

    public LRUCache(int capacity) {
        accessHistory = new DoublyLinkedList<>();
        lookup = new HashMap<>(capacity);
        maxCapacity = capacity;
    }
    
    public int get(int key) {
        final var entry = lookup.get(key);
        if (entry == null) return -1;
        moveFront(entry);
        return entry.value;
    }
    
    public void put(int key, int value) {
        if (lookup.containsKey(key)) {
            final var entry = lookup.get(key);
            entry.value = value;
            moveFront(entry);
            return;
        }
        if (lookup.size() == maxCapacity) {
            final var entry = accessHistory.removeLast();
            if (entry != null) lookup.remove(entry.key);
        }
        final var entry = new DoublyLinkedList.Entry<>(key, value);
        accessHistory.addFirst(entry);
        lookup.put(key, entry);
    }

    private void moveFront(DoublyLinkedList.Entry<Integer> entry) {
        accessHistory.remove(entry);
        accessHistory.addFirst(entry);
    }

    private static final class DoublyLinkedList<T> {
        private final Entry<T> head = new Entry<>(null, null);
        private Entry<T> tail = head;

        void remove(Entry<T> entry) {
            var prev = entry.previous;
            var next = entry.next;
            prev.next = next;
            if (next != null) {
                next.previous = prev;
            } else {
                tail = prev;
            }
            entry.next = null;
            entry.previous = null;
        }

        void addFirst(Entry<T> entry) {
            var first = head.next;
            head.next = entry;
            entry.previous = head;
            entry.next = first;
            if (first != null) {
                first.previous = entry;
            } else {
                tail = entry; // Update tail if it's the first real node
            }
        }

        Entry<T> removeLast() {
            if (tail == head) return null;
            var prev = tail.previous;
            prev.next = null;
            var oldValue = tail;
            tail = prev;
            return oldValue;
        }

        static final class Entry<V> {
            Integer key;
            V value;
            Entry<V> next;
            Entry<V> previous;
            Entry(Integer key, V value) {
                this.key = key;
                this.value = value;
                next = previous = null;
            }
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}

/*

4

put 1
put 2
put 3
put 4

put 2
put 4
put 5

======================
5 4 2 1
======================


 */
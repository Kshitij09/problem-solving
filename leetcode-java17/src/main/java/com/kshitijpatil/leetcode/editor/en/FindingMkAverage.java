//You are given two integers, m and k, and a stream of integers. You are tasked 
//to implement a data structure that calculates the MKAverage for the stream. 
//
// The MKAverage can be calculated using these steps: 
//
// 
// If the number of the elements in the stream is less than m you should 
//consider the MKAverage to be -1. Otherwise, copy the last m elements of the stream to 
//a separate container. 
// Remove the smallest k elements and the largest k elements from the container.
// 
// Calculate the average value for the rest of the elements rounded down to the 
//nearest integer. 
// 
//
// Implement the MKAverage class: 
//
// 
// MKAverage(int m, int k) Initializes the MKAverage object with an empty 
//stream and the two integers m and k. 
// void addElement(int num) Inserts a new element num into the stream. 
// int calculateMKAverage() Calculates and returns the MKAverage for the 
//current stream rounded down to the nearest integer. 
// 
//
// 
// Example 1: 
//
// 
//Input
//["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", 
//"calculateMKAverage", "addElement", "addElement", "addElement", 
//"calculateMKAverage"]
//[[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
//Output
//[null, null, null, -1, null, 3, null, null, null, 5]
//
//Explanation
//MKAverage obj = new MKAverage(3, 1); 
//obj.addElement(3);        // current elements are [3]
//obj.addElement(1);        // current elements are [3,1]
//obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements 
//exist.
//obj.addElement(10);       // current elements are [3,1,10]
//obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
//                          // After removing smallest and largest 1 element 
//the container will be [3].
//                          // The average of [3] equals 3/1 = 3, return 3
//obj.addElement(5);        // current elements are [3,1,10,5]
//obj.addElement(5);        // current elements are [3,1,10,5,5]
//obj.addElement(5);        // current elements are [3,1,10,5,5,5]
//obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
//                          // After removing smallest and largest 1 element 
//the container will be [5].
//                          // The average of [5] equals 5/1 = 5, return 5
// 
//
// 
// Constraints: 
//
// 
// 3 <= m <= 10⁵ 
// 1 <= k*2 < m 
// 1 <= num <= 10⁵ 
// At most 10⁵ calls will be made to addElement and calculateMKAverage. 
// 
//
// Related Topics Design Queue Heap (Priority Queue) Data Stream Ordered Set 👍 
//496 👎 137

package com.kshitijpatil.leetcode.editor.en;

import java.util.*;

public class FindingMkAverage {
    public static void main(String[] args) {
        MKAverage solution = new FindingMkAverage().new MKAverage(3,1);
        int[] adds = new int[] { 17612,74607,8272,33433,15456,64938 };
        for (int num : adds) solution.addElement(num);
        System.out.println(solution.calculateMKAverage());
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class MKAverage {
    private final int m, k;
    private final Queue<Integer> order;
    private final IntSortedList low, high;
    private final IntSortedList mid;
    private long midSum = 0;
    private int size = 0;
    public MKAverage(int m, int k) {
        this.m = m; this.k = k;
        low = new IntSortedList();
        mid = new IntSortedList();
        high = new IntSortedList();
        order = new ArrayDeque<>(m);
    }
    
    public void addElement(int num) {
        if (size == m && !order.isEmpty()) {
            int last = order.poll();
            remove(last);
        }
        add(num);
        order.offer(num);
    }

    public int calculateMKAverage() {
        if (size < m) return -1;
        return (int) (midSum / (m-2*k));
    }

    private void add(int num) {
        low.add(num);
        if (!low.isEmpty() && low.size() > k) {
            int maxLow = low.pollLast();
            mid.add(maxLow);
            midSum += maxLow;
        }
        if (!mid.isEmpty() && mid.size() > (m - 2*k)) {
            int maxMid = mid.pollLast();
            high.add(maxMid);
            midSum -= maxMid;
        }
        size++;
    }

    private void remove(int num) {
        if (!low.isEmpty() && num <= low.peekLast()) {
            low.remove(num);
        } else if (!mid.isEmpty() && num <= mid.peekLast()) {
            mid.remove(num);
            midSum -= num;
        } else {
            high.remove(num);
        }

        if (low.size() < k && !mid.isEmpty()) {
            var minMid = mid.pollFirst();
            low.add(minMid);
            midSum -= minMid;
        }
        if (mid.size() < (m-2*k) && !high.isEmpty()) {
            var minHigh = high.pollFirst();
            mid.add(minHigh);
            midSum += minHigh;
        }
        size--;
    }
    
    private final static class IntSortedList {
        private final TreeMap<Integer, Integer> freqMap = new TreeMap<>();
        private int size = 0;

        void add(int num) {
            freqMap.merge(num, 1, Integer::sum);
            size++;
        }

        void remove(int num) {
            int count = freqMap.getOrDefault(num, 0);
            if (count == 0) return;
            size--;
            if (count == 1) {
                freqMap.remove(num);
                return;
            }
            freqMap.put(num, count-1);
        }

        int peekFirst() {
            var entry = freqMap.firstEntry();
            if (entry == null) return -1;
            return entry.getKey();
        }

        int peekLast() {
            var entry = freqMap.lastEntry();
            if (entry == null) return -1;
            return entry.getKey();
        }

        int pollFirst() {
            var entry = freqMap.firstEntry();
            if (entry == null) return -1;
            remove(entry.getKey());
            return entry.getKey();
        }

        int pollLast() {
            var entry = freqMap.lastEntry();
            if (entry == null) return -1;
            remove(entry.getKey());
            return entry.getKey();
        }

        boolean isEmpty() {
            return freqMap.isEmpty();
        }

        int size() {
            return size;
        }
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
/*


[ 2 ] [ 5 4 3 ] [ 6 ]


5 4 6 2 3 1 7


 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class MedianFinder {

    /**
     * initialize your data structure here.
     */
    private Queue<Integer> minHeap;
    private Queue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
    }

    public void addNum(int num) {
        if (!minHeap.isEmpty() && num >= minHeap.peek())
            minHeap.add(num);
        else if (!maxHeap.isEmpty() && num <= maxHeap.peek())
            maxHeap.add(num);
        else
            maxHeap.add(num);
        
        while (minHeap.size() - maxHeap.size() >= 2) {
            int tmp = minHeap.poll();
            maxHeap.add(tmp);
        }

        while (maxHeap.size() - minHeap.size() >= 2) {
            int tmp = maxHeap.poll();
            minHeap.add(tmp);
        }
    }

    public double findMedian() {
        if (maxHeap.isEmpty()) return -1;
        if (minHeap.isEmpty()) return maxHeap.peek();
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2d;
        } else if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else
            return minHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());
        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());
    }


}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
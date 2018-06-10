package simple;

import java.util.LinkedList;

public class MinStack {

    private int size;
    private LinkedList<Integer> stack;
    private int min;
    private int minIndex;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        size = 0;
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.add(x);
        size++;
        if (size == 1) {
            min = x;
            minIndex = 0;
        } else {
            if (x < min) {
                min = x;
                minIndex = size - 1;
            }
        }
    }

    public void pop() {
        if (size == 0) return;
        stack.remove(size - 1);
        size--;
        if (size == 0) return;
        if (minIndex == size) {
            min = stack.get(0);
            for (int i = 1; i < size; i++) {
                int current = stack.get(i);
                if (current < min) {
                    min = current;
                    minIndex = i;
                }
            }
        }
    }

    public int top() {
        return stack.get(size - 1);
    }

    public int getMin() {
        if (size == 1) {
            return stack.get(0);
        }
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.pop();
        minStack.pop();
        minStack.pop();
    }

}

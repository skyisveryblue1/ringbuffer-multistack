package pgdp.ds;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Stack {
    Stack next;

    private int[] mem = null;
    private int top;
    private int capacity = 1;

    public Stack(int capacity) {
        this.capacity = capacity;
        mem = new int[capacity];
        top = 0;
    }

    public Stack() {
        mem = new int[capacity];
        top = 0;
    }

    public void push(int data) {
        if (top >= capacity) {
            capacity = capacity * 2;
            int[] new_mem = new int[capacity];
            for (int i = 0; i < top; ++i) {
                new_mem[i] = mem[i];
            }

            mem = new_mem;
        }

        mem[top] = data;
        top++;
    }

    public int pop() {
        if (top > 0) {
            top = top - 1;
            return mem[top];
        }

        return Integer.MIN_VALUE;
    }
    
    public int top() {
        if (top > 0) {
            return mem[top - 1];
        }

        return Integer.MIN_VALUE;
    }

    public int size() {
        return top;
    }

    public int get(int idx) {
        return (idx < top) ? mem[idx] : Integer.MIN_VALUE;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{\ncapacity = ").append(mem.length).append(",\n");
        sb.append("mem = [")
                .append(IntStream.range(0, top + 1).mapToObj(x -> "" + mem[x]).collect(Collectors.joining(", ")))
                .append("],\n");
        sb.append("next = ").append(next == null ? "null" : "\n" + next.toString()).append("\n}\n");
        return sb.toString();
    }
}


package pgdp.ds;

import java.util.Arrays;

public class RingBuffer {

    public static final int DEFAULT_CAPACITY = 100;
    private int[] mem;
    private int in;
    private int out;
    private int stored;
    private int capacity;

    public RingBuffer(int capacity) {
        this.capacity = capacity;
        this.mem = new int[this.capacity];
        this.in = 0;
        this.out = -1;
    }

    public int size() {
        return (out - in) + 1;
    }

    public boolean isEmpty() {
        return (out < in);
    }

    public boolean isFull() {
        return (size() == capacity);
    }

    public boolean put(int element) {
        if (!isFull()) {
            int nextOutSeq = out + 1;
            mem[nextOutSeq % capacity] = element;
            out++;
            return true;
        }
        return false;
    }
    
    public int get() {
        if (isEmpty()) {
            return Integer.MIN_VALUE;
        }
        int element = mem[in % capacity];
        in++;
        return element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RingBuffer := { capacity = ")
            .append(mem.length).append(", out = ").append(out).append(", in = ")
            .append(in).append(", stored = ").append(stored).append(", mem = ")
            .append(Arrays.toString(mem)).append(", buffer = [");
        if (!isEmpty()) {
            if(in >= 0 || in < mem.length) {
                int i = out;
                do {
                    sb.append(mem[i]).append(", ");
                    i = (i + 1) % mem.length;
                } while (i != in);
                sb.setLength(sb.length() - 2);
            } else {
                sb.append("Error: Field 'in' is <").append(in)
                    .append(">, which is out of bounds for an array of length ").append(mem.length);
            }
        }
        sb.append("] }");
        return sb.toString();
    }
}
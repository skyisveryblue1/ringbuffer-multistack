package test;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import pgdp.ds.RingBuffer;

public class RingBufferTest {

    public RingBuffer createRB(int capacity) {
        return new RingBuffer(capacity);
    }

    @Test
    public void isEmptyShouldInitiallyBeTrue() {
        final RingBuffer rb = createRB(2);
        assertTrue(rb.isEmpty(), "Ringer buffer should be empty if no value was added");
    }

    public @Test
    void isEmtpyShouldBeFalseWhenValueWasAdded() {
        final RingBuffer rb = createRB(2);
        rb.put(2);
        assertFalse(rb.isEmpty());
    }

    @Test
    public void isFullShouldBeTrueWhenCapacityIsZero() {
        final RingBuffer rb = createRB(0);
        assertTrue(rb.isFull());
    }

    @Test
    public void isFullShouldBeTrueWhenCapacityIsReached() {
        final int capacity = 3;
        final RingBuffer rb = createRB(capacity);
        for (int i = 0; i < capacity; i++) {
            rb.put(2);
        }
        assertTrue(rb.isFull());
    }

    @Test
    public void putShouldReturnTrueWhenValueCouldBeAdded() {
        final RingBuffer rb = createRB(1);
        final boolean result = rb.put(1);
        assertTrue(result);
    }

    @Test
    public void putShouldReturnFalseWhenValueCouldNotBeAdded() {
        final RingBuffer rb = createRB(1);
        rb.put(1);
        final boolean result = rb.put(1);
        assertFalse(result);
    }

    @Test
    public void getShouldReturnPutValue() {
        final RingBuffer rb = createRB(1);
        final int value = 3;
        rb.put(value);
        final int actual = rb.get();
        assertEquals(value, actual);
    }

    @Test
    public void getShouldReturnLongestAliveValue() {
        final int longestAlive = 4;
        final RingBuffer rb =  createRB(longestAlive);
        for (int i = longestAlive; i > 0; i--) {
            rb.put(i);
        }
        final int actual = rb.get();
        assertEquals(longestAlive, actual);
    }

    @Test
    public void getShouldReturnMinValueWhenThereIsNoValue() {
        final RingBuffer rb = createRB(2);
        final int actual = rb.get();
        assertEquals(Integer.MIN_VALUE, rb.get());
    }

    @Test
    public void shouldHandleOverflow() {
        final RingBuffer rb = createRB(2);
        rb.put(1);
        rb.put(2);
        rb.put(3);
        assertEquals(1, rb.get());
        assertEquals(2, rb.get());
        assertEquals(Integer.MIN_VALUE, rb.get());
    }

    // Some integration tests

    @Test
    public void integrationTest1() {
        final RingBuffer rb = createRB(4);
        final int expected = 10;
        rb.put(100);
        rb.put(2);
        rb.put(9);
        rb.get();
        rb.get();
        rb.put(expected);
        rb.get();
        final int actual = rb.get();
        assertEquals(expected, actual);
    }

    @Test
    public void integrationTest2() {
        final RingBuffer rb = createRB(2);
        final int expected = 100;
        rb.put(10);
        rb.put(2);
        rb.get();
        rb.put(expected);
        rb.get();
        final int actual = rb.get();
        assertEquals(expected, actual);
    }

}

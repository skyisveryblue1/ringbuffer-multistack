package test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import pgdp.ds.MultiStack;
import pgdp.ds.Stack;

public class MultiStackTest {


    private static int[] array(int... values) {
        return values;
    }

    private static boolean arrayContains(int[] arr, int value) {
        return Arrays.stream(arr).anyMatch(value1 -> value1 == value);
    }

    public MultiStack createMS() {
        return new MultiStack();
    }
    
    MultiStack stack;


    @Test
    public void pushShouldAddValue() {
        stack = createMS();
        final int value = 20;
        stack.push(value);
        final int[] expectedMem = array(value);
        assertArrayEquals(expectedMem, stack.mem(stack.head()));
    }

    @Test
    public void pushOutOfCapacityShouldCreateNewStack() {
        stack = createMS();
        final int value = 20;
        stack.push(-1);
        stack.push(value);
        final Stack next = stack.next(stack.head());
        final int[] mem = stack.mem(next);
        assertEquals(mem[0], value);
    }

    @Test
    public void topShouldReturnLastPushedValue() {
        final int expected = 100;
        stack.push(200);
        stack.push(600);
        stack.push(expected);
        assertEquals(expected, stack.top());
    }

    @Test
    public void popShouldNotRemoveFirstStack() {
        stack = createMS();
        stack.pop();
        assertNotNull(stack.head());
    }

    @Test
    public void popShouldReturnLastPushedValue() {
        stack = createMS();
        final int expected = 100;
        stack.push(200);
        stack.push(600);
        stack.push(expected);
        assertEquals(expected, stack.pop());
    }

    @Test
    public void popShouldRemoveValueFromStack() {
        stack = createMS();
        final int val1 = 200;
        final int val2 = 600;
        stack.push(val1);
        stack.push(val2);
        stack.push(600);
        stack.pop();
        
        assertTrue(arrayContains(stack.mem(stack.next(stack.head())), val2));
    }

    @Test
    public void popShouldRemoveEmptyStack() {
        stack = createMS();
        stack.push(200);
        stack.push(200);
        stack.pop();
        assertNull(stack.next(stack.head()));
    }

    @Test
    public void emptyStackShouldReturnMinValue() {
        stack = createMS();
        assertEquals(Integer.MIN_VALUE, stack.top());
    }

    @Test
    public void emptyStackShouldReturnMinValueOnPop() {
        stack = createMS();
        assertEquals(Integer.MIN_VALUE, stack.pop());
    }

    @Test
    public void pushShouldNotChangeStacks() {
        stack = createMS();
        final Stack st = stack.head();
        st.push(200);
        assertEquals(st, stack.head());
    }

    @Test
    public void popShouldNotChangeStacks() {
        final Stack st = stack.head();
        stack.push(200);
        stack.pop();
        assertEquals(st, stack.head());
    }

    @Test
    public void appendedStacksShouldAlwaysHaveDoubleCapacity() {
        stack = createMS();
        final int stacksToCreate = 10;
        int valuesToPush = 1;
        for (int i = 0; i < stacksToCreate; i++) {
            for (int j = 0; j < valuesToPush; j++) {
                stack.push(j);
            }
            valuesToPush *= 2;
        }
        Stack current = stack.head();
        int expectedCapacity = 1;
        for (int i = 0; i < stacksToCreate; i++) {
            assertEquals(expectedCapacity, stack.mem(current).length);
            current = stack.next(current);
            expectedCapacity *= 2;
        }
    }

    @Test
    public void topShouldNotRemoveValue() {
        stack = createMS();
        final int value = 200;
        stack.push(value);
        stack.top();
        assertArrayEquals(array(value), stack.mem(stack.head()));
    }

    @Test
    public void pushShouldNotChangeHead() {
        stack = createMS();
        final Stack head = stack.head();
        stack.push(200);
        stack.push(200);
        stack.push(200);
        assertEquals(head, stack.head());
    }

    @Test
    public void topShouldReturnCorrectValues() {
        stack = createMS();
        final int value = 10;
        final int value2 = 20;
        final int value3 = 30;
        final int value4 = 40;

        stack.push(value);
        assertEquals(value, stack.top());
        stack.push(value2);
        assertEquals(value2, stack.top());
        stack.push(value3);
        assertEquals(value3, stack.top());
        stack.push(value4);
        assertEquals(value4, stack.top());

        assertArrayEquals(array(value), stack.mem(stack.head()));
        assertArrayEquals(array(value2, value3), stack.mem(stack.next(stack.head())));
        assertEquals(value4, stack.mem(stack.next(stack.next(stack.head())))[0]);
    }


    @Test
    public void popShouldReturnCorrectValues() {
        stack = createMS();
        final int value = 10;
        final int value2 = 20;
        final int value3 = 30;
        final int value4 = 40;

        stack.push(value);
        assertEquals(value, stack.pop());

        stack.push(value2);
        stack.push(value3);
        assertEquals(value3, stack.pop());

        assertArrayEquals(array(value2), stack.mem(stack.head()));

        stack.push(value4);
        stack.push(value3);
        assertEquals(value3, stack.pop());
        assertEquals(value4, stack.pop());
        assertEquals(value2, stack.pop());

        assertEquals(Integer.MIN_VALUE, stack.pop());
    }
}

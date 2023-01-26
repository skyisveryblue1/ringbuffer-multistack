package pgdp.ds;

import test.MultiStackTest;
import test.RingBufferTest;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MultiStackTest test1 = new MultiStackTest();
        test1.pushShouldAddValue();
        test1.pushOutOfCapacityShouldCreateNewStack();
        test1.topShouldReturnLastPushedValue();
        test1.popShouldNotRemoveFirstStack();
        test1.popShouldReturnLastPushedValue();
        test1.popShouldRemoveValueFromStack();
        test1.popShouldRemoveEmptyStack();
        test1.emptyStackShouldReturnMinValue();
        test1.emptyStackShouldReturnMinValueOnPop();
        test1.pushShouldNotChangeStacks();
        test1.appendedStacksShouldAlwaysHaveDoubleCapacity();
        test1.topShouldNotRemoveValue();
        test1.pushShouldNotChangeHead();
        test1.topShouldReturnCorrectValues();
        test1.popShouldReturnCorrectValues();
        
        RingBufferTest test = new RingBufferTest();
        test.isEmptyShouldInitiallyBeTrue();
        test.isEmtpyShouldBeFalseWhenValueWasAdded();
        test.isFullShouldBeTrueWhenCapacityIsZero();
        test.isFullShouldBeTrueWhenCapacityIsReached();
        test.putShouldReturnTrueWhenValueCouldBeAdded();
        test.putShouldReturnFalseWhenValueCouldNotBeAdded();
        test.getShouldReturnPutValue();
        test.getShouldReturnLongestAliveValue();
        test.getShouldReturnMinValueWhenThereIsNoValue();
        test.shouldHandleOverflow();
        test.integrationTest1();
        test.integrationTest2();
        
        
    }
    
}

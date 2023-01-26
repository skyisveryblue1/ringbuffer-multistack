package pgdp.ds;

import java.util.ArrayList;

public class MultiStack {
 
    ArrayList<Stack> stacks;
    
    public MultiStack() {
        stacks = new ArrayList<Stack>();
        stacks.add(new Stack());
    }

    public void push(int data) {
        
        for (int i = 0; i < stacks.size(); ++i) {
            if (stacks.get(i).size() < (1 << i)) {
                stacks.get(i).push(data);
                return;
            }
        }
        
        Stack newStack = new Stack();
        newStack.push(data);
        stacks.add(newStack);
    }

    public int pop() {
        for (int i = 0; i < stacks.size(); ++i) {
            if (stacks.get(i).size() > 0 && stacks.get(i).size() < (1 << i)) {
                int ret = (Integer)stacks.get(i).pop();
                if (stacks.get(i).size() == 0 && i != 0) {
                    stacks.remove(i);
                }
                return ret;
            }
        }
        
        for (int i = stacks.size() - 1; i >= 0; --i) {
            if (stacks.get(i).size() == (1 << i)) {
                int ret = (Integer)stacks.get(i).pop();
                if (stacks.get(i).size() == 0 && i != 0) {
                    stacks.remove(i);
                }
                return ret;
            }
        }
        
        return Integer.MIN_VALUE;
    }
    
    public int top() {
        for (int i = 0; i < stacks.size(); ++i) {
            if (stacks.get(i).size() > 0 && stacks.get(i).size() < (1 << i)) {
                return (Integer)stacks.get(i).top();
            }
        }
        
        for (int i = stacks.size() - 1; i >= 0; --i) {
            if (stacks.get(i).size() == (1 << i)) {
                return (Integer)stacks.get(i).top();
            }
        }
        return Integer.MIN_VALUE;
    }
    
    public Stack head() {
        return stacks.get(0);
    }
    
    public Stack next(Stack s) {
        int idx = stacks.indexOf(s);
        return idx < stacks.size() - 1 ? stacks.get(idx + 1) : null;
    }

    
    public int[] mem(Stack s) {
        int[] ret = new int[s.size()];
        for (int j = 0; j < s.size() ; ++j) {
            ret[j] = (Integer)s.get(j);
        }
        return ret;
    }

    @Override
    public String toString() {
        return stacks.toString();
    }
}


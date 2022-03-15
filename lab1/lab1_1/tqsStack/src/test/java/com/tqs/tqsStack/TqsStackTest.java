package com.tqs.tqsStack;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.*;

class TqsStackTest {
    private TqsStack<Integer> stack; 

    @BeforeEach
    void setUp() {
        stack = new TqsStack<Integer>();
    }

    @Test
    @DisplayName("TEST - Stack is empty on construction")
    void emptyOnConstruction(){
        assertTrue(stack.isEmpty(), "[FAILED TEST] Stack isn't empty on construction");
    }

    @Test
    @DisplayName("TEST - Stack has size 0 on construction")
    void size0OnConstruction(){
        assertEquals(stack.size(), 0, "[FAILED TEST] Stack hasn't size 0 on construction");
    }

    @Test
    @DisplayName("TEST - After n pushes to an empty stack, n > 0, the stack is not empty and its size is n")
    void testPushesToEmptyStack(){
        for (int i = 0; i < 5; i++) {
            stack.push(i);            
        }
        assertFalse(stack.isEmpty(), "[FAILED TEST] After n pushes to an empty stack, n > 0, the stack is empty");
        assertEquals(stack.size(), 5, "[FAILED TEST] After n pushes to an empty stack, n > 0, the stack hasn't size n");
    }   

    @Test
    @DisplayName("TEST - If one pushes x then pops, the value popped is x.")
    void testPushThenPop(){
        stack.push(1);
        assertEquals(stack.pop(), 1, "[FAILED TEST] If one pushes x then pops, the value popped isn't x.");
    }

    @Test
    @DisplayName("TEST - If one pushes x then peeks, the value returned is x, but the size stays the same.")
    void testPushThenPeek(){
        stack.push(1);
        int sizeBeforePeeks = stack.size();
        assertEquals(stack.peek(), 1, "[FAILED TEST] If one pushes x then peeks, the value returned isn't x.");
        assertEquals(sizeBeforePeeks, stack.size(), "[FAILED TEST] If one pushes x then peeks, the size doesn't stays the same.");
    }

    @Test
    @DisplayName("TEST - If the size is n, then after n pops, the stack is empty and has a size 0")
    void testPoppingDownToEmpty(){
        int numberOfPushes = (int) (Math.random() * 20 + 1);
        for (int i = 0; i < numberOfPushes; i++) {
            stack.push(i);
        }
        for (int i = 0; i < numberOfPushes; i++) {
            stack.pop();
        }
        assertTrue(stack.isEmpty(), "[FAILED TEST] If the size is n, then after n pops, the stack isn't empty");
        assertEquals(stack.size(), 0, " [FAILED TEST] If the size is n, then after n pops, the stack hasn't a size 0");
    }

    @Test
    @DisplayName("TEST - Popping from an empty stack does throw a NoSuchElementException")
    void testPopOnEmptyStack() throws Exception {
        assertThrows(NoSuchElementException.class, () -> {
            stack.pop();
        }, "[FAILED TEST] Popping from an empty stack doesn't throw a NoSuchElementException");
    }

    @Test
    @DisplayName("TEST - Peeking into an empty stack does throw a NoSuchElementException")
    void testPeekIntoEmptyStack(){
        assertThrows(NoSuchElementException.class, () -> {
            stack.peek();
        }, "[FAILED TEST] Peeking into an empty stack doesn't throw a NoSuchElementException");
    }

    @Test
    @DisplayName("TEST - For bounded stacks only: pushing onto a full stack does throw an IllegalStateException ")
    void testPushOntoAfullStack_ForBoundedStacks(){
        stack = new TqsStack<>(1);
        stack.push(1);

        assertThrows(IllegalStateException.class, () -> {
            stack.push(1);
        }, "[FAILED TEST] For bounded stacks only: pushing onto a full stack doesn't throw an IllegalStateException");
    }

}

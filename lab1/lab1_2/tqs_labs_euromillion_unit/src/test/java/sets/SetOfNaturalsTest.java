/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class SetOfNaturalsTest {
    private SetOfNaturals setA;
    private SetOfNaturals setB;
    private SetOfNaturals setC;
    private SetOfNaturals setD;

    @BeforeEach
    public void setUp() {
        setA = new SetOfNaturals();
        setB = SetOfNaturals.fromArray(new int[]{10, 20, 30, 40, 50, 60});

        setC = new SetOfNaturals();
        for (int i = 5; i < 50; i++) {
            setC.add(i * 10);
        }
        setD = SetOfNaturals.fromArray(new int[]{30, 40, 50, 60, 10, 20});
    }

    @AfterEach
    public void tearDown() {
        setA = setB = setC = setD = null;
    }

    @Test
    public void testAddElement() {

        setA.add(99);
        assertTrue(setA.contains(99), "add: added element not found in set.");
        assertEquals(1, setA.size());

        setB.add(11);
        assertTrue(setB.contains(11), "add: added element not found in set.");
        assertEquals(7, setB.size(), "add: elements count not as expected.");

        assertThrows(IllegalArgumentException.class, () -> setB.add(10), "add: added duplicate element");
        assertThrows(IllegalArgumentException.class, () -> setB.add(0), "add: added not a natural number");

    }

    @Test
    public void testAddBadArray() {
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> setA.add(elems)); //add: added not a natural numbers
        assertThrows(IllegalArgumentException.class, () -> setB.add(new int[]{10, 20}), "add: added duplicates elements with array");

        setB.add(new int[]{70, 80});
        assertTrue(setB.contains(70) && setB.contains(80), "add: added elements not found in set with array.");
        assertEquals(8, setB.size(), "add: elements count not as expected with array.");

    }


    @Test
    public void testIntersectForNoIntersection() {
        assertFalse(setA.intersects(setB), "no intersection but was reported as existing");
        assertTrue(setB.intersects(setD), "intersects: {10, 20, 30, 40, 50, 60} should be intersects {30, 40, 50, 60, 10, 20}, but return false.");
    }

    @Test
    public void testFromArray(){
        int[] elems = new int[]{10, 20, -30};

        // must fail with exception
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(elems)); //add: added not a natural numbers
        assertThrows(IllegalArgumentException.class, () -> SetOfNaturals.fromArray(new int[]{10, 10}), "add: added duplicates elements with array");

        SetOfNaturals set = SetOfNaturals.fromArray(new int[]{70, 80});
        assertTrue(set.contains(70) && set.contains(80), "fromArray: elements not found in set.");
        assertEquals(2, set.size(), "fromArray: elements count not as expected.");   
    }

    @Test
    public void testContains(){
        assertTrue(setB.contains(10), "contains: {10, 20, 30, 40, 50, 60} contains 10, but return false");
        assertFalse(setB.contains(1), "contains: {10, 20, 30, 40, 50, 60} doesn't contains 1, but return true");
    }

}

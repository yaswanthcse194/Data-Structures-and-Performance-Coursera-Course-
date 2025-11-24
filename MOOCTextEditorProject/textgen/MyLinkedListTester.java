package textgen;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyLinkedListTester {

    MyLinkedList<Integer> list;

    @Before
    public void setup() {
        list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void testGet() {
        assertEquals((Integer)1, list.get(0));
        assertEquals((Integer)2, list.get(1));
        assertEquals((Integer)3, list.get(2));
        assertEquals((Integer)4, list.get(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetLowerBound() { list.get(-1); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetUpperBound() { list.get(4); }

    @Test
    public void testAddEnd() {
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        assertEquals(0, newList.size());
        
        newList.add(5);
        assertEquals(1, newList.size());
        assertEquals((Integer)5, newList.get(0));
        
        newList.add(6);
        assertEquals(2, newList.size());
        assertEquals((Integer)5, newList.get(0));
        assertEquals((Integer)6, newList.get(1));
        
        newList.add(7);
        assertEquals(3, newList.size());
        assertEquals((Integer)5, newList.get(0));
        assertEquals((Integer)6, newList.get(1));
        assertEquals((Integer)7, newList.get(2));
    }

    @Test
    public void testAddAtIndex() {
        list.add(1, 10);
        assertEquals((Integer)10, list.get(1));
        assertEquals((Integer)2, list.get(2));
        assertEquals(5, list.size());
        
        // Test adding at beginning
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        newList.add(0, 1);
        assertEquals(1, newList.size());
        assertEquals((Integer)1, newList.get(0));
        
        newList.add(0, 0);
        assertEquals(2, newList.size());
        assertEquals((Integer)0, newList.get(0));
        assertEquals((Integer)1, newList.get(1));
        
        // Test adding at end
        newList.add(2, 2);
        assertEquals(3, newList.size());
        assertEquals((Integer)0, newList.get(0));
        assertEquals((Integer)1, newList.get(1));
        assertEquals((Integer)2, newList.get(2));
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() { list.add(null); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddLowerBound() { list.add(-1, 5); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddUpperBound() { list.add(list.size() + 1, 5); }

    @Test(expected = NullPointerException.class)
    public void testAddAtIndexNull() { list.add(0, null); }

    @Test
    public void testRemove() {
        int sizeBefore = list.size();
        Integer removed = list.remove(1);
        assertEquals((Integer)2, removed);
        assertEquals(sizeBefore - 1, list.size());
        assertEquals((Integer)3, list.get(1));
        
        // Test removing first element
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        newList.add(1);
        newList.add(2);
        newList.add(3);
        Integer firstRemoved = newList.remove(0);
        assertEquals((Integer)1, firstRemoved);
        assertEquals(2, newList.size());
        assertEquals((Integer)2, newList.get(0));
        assertEquals((Integer)3, newList.get(1));
        
        // Test removing last element
        Integer lastRemoved = newList.remove(1);
        assertEquals((Integer)3, lastRemoved);
        assertEquals(1, newList.size());
        assertEquals((Integer)2, newList.get(0));
        
        // Test removing only element
        Integer onlyRemoved = newList.remove(0);
        assertEquals((Integer)2, onlyRemoved);
        assertEquals(0, newList.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveLowerBound() { list.remove(-1); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveUpperBound() { list.remove(list.size()); }

    @Test
    public void testSet() {
        Integer old = list.set(1, 20);
        assertEquals((Integer)2, old);
        assertEquals((Integer)20, list.get(1));
        assertEquals(4, list.size());
        
        // Test setting first element
        Integer firstOld = list.set(0, 10);
        assertEquals((Integer)1, firstOld);
        assertEquals((Integer)10, list.get(0));
        
        // Test setting last element
        Integer lastOld = list.set(3, 40);
        assertEquals((Integer)4, lastOld);
        assertEquals((Integer)40, list.get(3));
    }

    @Test
    public void testSize() {
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        assertEquals(0, newList.size());
        
        newList.add(1);
        assertEquals(1, newList.size());
        
        newList.add(2);
        assertEquals(2, newList.size());
        
        newList.add(0, 0);
        assertEquals(3, newList.size());
        
        newList.remove(1);
        assertEquals(2, newList.size());
        
        newList.remove(0);
        assertEquals(1, newList.size());
        
        newList.remove(0);
        assertEquals(0, newList.size());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNull() { list.set(0, null); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetLowerBound() { list.set(-1, 5); }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetUpperBound() { list.set(list.size(), 5); }
}

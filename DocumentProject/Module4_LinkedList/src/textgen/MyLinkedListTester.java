package textgen;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MyLinkedListTester {

    private MyLinkedList<Integer> shortList;
    private MyLinkedList<Integer> emptyList;
    private MyLinkedList<Integer> longerList;

    @Before
    public void setUp() throws Exception {
        // Initialize lists
        shortList = new MyLinkedList<>();
        shortList.add(1);
        shortList.add(2);

        emptyList = new MyLinkedList<>();

        longerList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            longerList.add(i);
        }
    }

    /** Test get method */
    @Test
    public void testGet() {
        // shortList: [1, 2]
        assertEquals((Integer)1, shortList.get(0));
        assertEquals((Integer)2, shortList.get(1));

        // longerList: [0..9]
        for (int i = 0; i < 10; i++) {
            assertEquals((Integer)i, longerList.get(i));
        }
    }

    /** Test add at end */
    @Test
    public void testAddEnd() {
        emptyList.add(5);
        assertEquals((Integer)5, emptyList.get(0));
        assertEquals(1, emptyList.size());

        shortList.add(3);
        assertEquals((Integer)3, shortList.get(2));
        assertEquals(3, shortList.size());
    }
}

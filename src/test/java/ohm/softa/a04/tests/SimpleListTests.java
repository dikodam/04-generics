package ohm.softa.a04.tests;

import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {
    
    private final Logger logger = LogManager.getLogger();
    private SimpleList<Integer> testList;
    
    @BeforeEach
    void setup() {
        testList = new SimpleListImpl<>();
        
        testList.add(1);
        testList.add(2);
        testList.add(3);
        testList.add(4);
        testList.add(5);
    }
    
    @Test
    @DisplayName("Testing if adding and iterating elements is implemented correctly")
    void testAddElements() {
        logger.info("Testing if adding and iterating elements is implemented correctly");
        int counter = 0;
        for (int i : testList) {
            counter++;
        }
        assertEquals(5, counter);
    }
    
    @Test
    void testSize() {
        logger.info("Testing if size() method is implemented correctly");
        assertEquals(5, testList.size());
    }
    
    @Test
    void testFilterAnonymousClass() {
        logger.info("Testing the filter possibilities by filtering for all elements greater 2");
        SimpleList result = testList.filter(new SimpleFilter<Integer>() {
            @Override
            public boolean include(Integer item) {
                return item > 2;
            }
        });
        
        for (Object o : result) {
            int i = (int) o;
            assertTrue(i > 2);
        }
    }
    
    @Test
    void testFilterLambda() {
        logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
        SimpleList<Integer> result = testList.filter(i -> i % 2 == 0);
        for (Integer i : result) {
            assertTrue(i % 2 == 0);
        }
    }
    
    @Test
    void get() {
        IntStream.range(0, 5)
                 .forEach((i) -> assertEquals((int) testList.get(i), i + 1));
    }
    
    @Test
    void getThrowsOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(5));
    }
    
    @Test
    void map() {
        SimpleList<Integer> result = testList.map(i -> i * 2);
        for (int i = 0; i < testList.size(); i++) {
            int expected = testList.get(i) * 2;
            int acutal = result.get(i);
            assertEquals(expected, acutal);
        }
    }
}
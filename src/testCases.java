import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testCases {

    @Test
    public void testPositiveNumbers() {
        assertEquals(10, AddNumbers.sum(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(2, AddNumbers.sum(new int[]{2}));
    }

    @Test
    public void testIncludingZero() {
        assertEquals(6, AddNumbers.sum(new int[]{0, 1, 2, 3}));
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(-6, AddNumbers.sum(new int[]{-1, -2, -3}));
    }

    @Test
    public void testMixedNumbers() {
        assertEquals(4, AddNumbers.sum(new int[]{-1, 2, 3}));
    }
}

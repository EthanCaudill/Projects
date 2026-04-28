import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SelectionSortTest {

    @Test
    public void testReverseArray() {
        int[] input = {3,2,1};
        int[] expected = {1,2,3};
        assertArrayEquals(expected, SelectionSort.doSelectionSort(input));
    }

    @Test
    public void testAlreadySorted() {
        int[] input = {1,2,3};
        int[] expected = {1,2,3};
        assertArrayEquals(expected, SelectionSort.doSelectionSort(input));
    }

    @Test
    public void testTwoElements() {
        int[] input = {2,1};
        int[] expected = {1,2};
        assertArrayEquals(expected, SelectionSort.doSelectionSort(input));
    }

    @Test
    public void testSingleElement() {
        int[] input = {5};
        int[] expected = {5};
        assertArrayEquals(expected, SelectionSort.doSelectionSort(input));
    }
}
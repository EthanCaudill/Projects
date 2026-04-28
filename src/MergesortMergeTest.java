import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;


public class MergesortMergeTest {

    @Test
    public void testBothArraysEmpty() {
        double[] a = {};
        double[] b = {};
        double[] expected = {};
        assertArrayEquals(expected, Mergesort.merge(a, b));
    }

    @Test
    public void testOnlyAEmpty() {
        double[] a = {};
        double[] b = {1, 2, 3};
        double[] expected = {1, 2, 3};
        assertArrayEquals(expected, Mergesort.merge(a, b));
    }

    @Test
    public void testOnlyBEmpty() {
        double[] a = {1, 2, 3};
        double[] b = {};
        double[] expected = {1, 2, 3};
        assertArrayEquals(expected, Mergesort.merge(a, b));
    }

    @Test
    public void testASmallNumbers() {
        double[] a = {1, 3};
        double[] b = {2, 4};
        double[] expected = {1, 2, 3, 4};
        assertArrayEquals(expected, Mergesort.merge(a, b));
    }

    @Test
    public void testBSmallNumbers() {
        double[] a = {5, 6};
        double[] b = {1, 2};
        double[] expected = {1, 2, 5, 6};
        assertArrayEquals(expected, Mergesort.merge(a, b));
    }
}
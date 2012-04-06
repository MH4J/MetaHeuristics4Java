package de.mh4j.examples;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

import org.testng.annotations.Test;

public class SortingTest {

    @Test
    public void testCreateWithArray() {
        int[] testdata = new int[100];
        for (int i = 0; i < testdata.length; i++) {
            testdata[i] = (i + 7) % 100;
        }

        Sorting sorting = new Sorting(testdata);

        assertEquals(testdata, sorting.getNumbers());
    }

    @Test
    public void testCreateWithDuplicateNumbersArray() {
        int[] testdata = new int[100];
        testdata[0] = 1;
        testdata[1] = 2;
        testdata[2] = 2;
        testdata[3] = 3;

        try {
            new Sorting(testdata);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals("Input array contains at least one duplicate number at index 2", exception.getMessage());
        }
    }

    @Test
    public void testGetCostsWithPerfectOrder() {
        int[] testdata = new int[100];
        for (int i = 0; i < testdata.length; i++) {
            testdata[i] = i;
        }

        Sorting sorting = new Sorting(testdata);

        assert sorting.getCosts() == 0 : "Sorting costs for perfect ordering should be zero";
    }

    @Test
    public void testGetCostsWithWorstOrder() {
        int[] testdata = new int[100];
        for (int i = 99; i >= 0; i--) {
            testdata[99 - i] = i;
        }

        Sorting sorting = new Sorting(testdata);

        assert sorting.getCosts() == testdata.length : "Sorting costs for the worst order should be equal to the number of elements in the array";
    }

    @Test
    public void testGetCostsWithBadOrder() {
        int[] testdata = new int[100];
        for (int i = 0; i < 100; i++) {
            testdata[i] = i;
        }

        testdata[10] = 20;
        testdata[20] = 30;
        testdata[30] = 10;

        Sorting sorting = new Sorting(testdata);

        assert sorting.getCosts() == 3 : "Sorting costs for this order should be 3";
    }

    @Test
    public void testTrivialIsBetterThan() {
        int[] badSortingArray = new int[100];
        int[] goodSortingArray = new int[100];
        for (int i = 0; i < 100; i++) {
            goodSortingArray[i] = i;
            badSortingArray[99 - i] = i;
        }

        Sorting badSorting = new Sorting(badSortingArray);
        Sorting goodSorting = new Sorting(goodSortingArray);

        assert goodSorting.isBetterThan(badSorting) : "Good sorting should be better than bad sorting";
    }
}

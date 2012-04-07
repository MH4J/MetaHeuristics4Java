package de.mh4j.examples;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;
import static org.testng.AssertJUnit.fail;

import java.util.Arrays;

import org.testng.annotations.Test;

public class SortingTest {

    @Test
    public void testCreateWithArray() {
        int[] testdata = new int[100];
        for (int i = 0; i < testdata.length; i++) {
            testdata[i] = (i + 7) % 100;
        }

        Sorting sorting = new Sorting(testdata);

        assertEquals(testdata, sorting.numbers);
    }

    @Test
    public void testCreateWithNumbers() {
        Sorting sorting = new Sorting(0, 1, 3, 5, 6, 4, 2);

        assert Arrays.equals(new int[] { 0, 1, 3, 5, 6, 4, 2 }, sorting.numbers);
    }

    @Test
    public void testCreateWithEmptyArray() {
        try {
            new Sorting(new int[] {});
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals("Input array can not be empty", exception.getMessage());
        }
    }

    @Test
    public void testCreateWithToFewNumbers() {
        try {
            new Sorting(1, 98, 99);
            fail("Should have thrown an IllegalArgumentException");
        } catch (IllegalArgumentException exception) {
            assertEquals(
                    "Input array does not contain enough numbers (highest number is 99 but there were 3 entries in zero based array)",
                    exception.getMessage());
        }
    }

    @Test
    public void testCreateWithDuplicateNumbersArray() {
        try {
            new Sorting(1, 2, 2, 3);
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

    @Test
    public void testGetRandomSorting() {
        int amountOfNumbers = 100;
        Sorting sorting = Sorting.createRandomSorting(amountOfNumbers);

        assert sorting.getNumbers().length == amountOfNumbers : "Randomly created sorting should have the request amount of numbers in it";
    }

    @Test
    public void testEquals() {
        Sorting sorting = new Sorting(0, 1, 2, 3, 4);
        Sorting equalSorting = new Sorting(0, 1, 2, 3, 4);
        Sorting otherSorting = new Sorting(0, 4, 2, 1, 3);

        assert sorting.equals(equalSorting) == true;
        assert equalSorting.equals(sorting) == true;
        assert sorting.equals(otherSorting) == false;
    }

    @Test
    public void testCopyConstructor() {
        Sorting sorting = Sorting.createRandomSorting(100);
        Sorting copy = new Sorting(sorting);

        assertEquals(copy, sorting);
        assertNotSame(copy, sorting);
    }
}

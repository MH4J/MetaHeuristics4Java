package de.mh4j.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mh4j.solver.Solution;

/**
 * A Sorting represents a not necessarily ordered array of the numbers from 0 to
 * 99. This is the {@link Solution} class for the first set of example
 * implementations of MH4J.<br>
 * <br>
 * The goal of the optimization algorithms is to find the correct arrangement of
 * the numbers, so that each number stands at its correct array index (number
 * zero is at array index 0 and number three is at index 3)<br>
 * <br>
 * Of course the correct solution to this problem is trivial, but this is only
 * an easy example implementation that should help you to understand the
 * concrete algorithm implementations.
 */
public class Sorting implements Solution<Sorting> {
    private static final Logger log = LoggerFactory.getLogger(Sorting.class);

    protected final int[] numbers;
    protected int costs;

    public Sorting(int... numbers) {
        this.numbers = numbers;
        checkSize();
        checkForDuplicates();
        calculateCosts();
    }

    public Sorting(Sorting otherSorting) {
        this.numbers = Arrays.copyOf(otherSorting.numbers, otherSorting.numbers.length);
        this.costs = otherSorting.costs;
    }

    private void checkSize() throws IllegalArgumentException {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Input array can not be empty");
        }

        int highestNumber = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > highestNumber) {
                highestNumber = numbers[i];
            }
        }

        if (numbers.length != highestNumber + 1) {
            throw new IllegalArgumentException("Input array does not contain enough numbers (highest number is "
                    + highestNumber + " but there were " + numbers.length + " entries in zero based array)");
        }
    }

    private void checkForDuplicates() throws IllegalArgumentException {
        boolean[] numberExists = new boolean[numbers.length];
        for (int i = 0; i < numberExists.length; i++) {
            numberExists[i] = false;
        }

        for (int i = 0; i < numbers.length; i++) {
            int number = numbers[i];
            if (numberExists[number] == true) {
                throw new IllegalArgumentException("Input array contains at least one duplicate number at index " + i);
            }
            else {
                numberExists[number] = true;
            }
        }
    }

    private void calculateCosts() {
        costs = 0;
        for (int i = 0; i < numbers.length; i++) {
            costs += calculateCostsAtIndex(i);
        }
    }

    private int calculateCostsAtIndex(int index) {
        return Math.abs(index - numbers[index]);
    }

    @Override
    public int getCosts() {
        return costs;
    }

    @Override
    public boolean isBetterThan(Sorting otherSorting) {
        return this.getCosts() < otherSorting.getCosts();
    }

    public int[] getNumbers() {
        return numbers;
    }

    public int getAmountOfNumbers() {
        return numbers.length;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (otherObject instanceof Sorting) {
            Sorting otherSorting = (Sorting) otherObject;
            return Arrays.equals(this.numbers, otherSorting.numbers);
        }
        else {
            return false;
        }
    }

    /**
     * Creates a new Sorting instance with a random non-repeating numbers array.
     * 
     * @param amountOfNumbers
     *            Determines how many different numbers will be used in this
     *            sorting (i.e. the size of the underlying array)
     */
    public static Sorting createRandomSorting(int amountOfNumbers) {
        return Sorting.createRandomSorting(amountOfNumbers, System.nanoTime());
    }

    /**
     * Creates a new Sorting instance with a random non-repeating numbers array.
     * 
     * @param amountOfNumbers
     *            Determines how many different numbers will be used in this
     *            sorting (i.e. the size of the underlying array)
     * @param seed
     *            for the random generator
     */
    public static Sorting createRandomSorting(int amountOfNumbers, long seed) {
        log.debug("create random sorting using seed {}", seed);

        ArrayList<Integer> remainingNumbers = new ArrayList<>(amountOfNumbers);
        for (int i = 0; i < amountOfNumbers; i++) {
            remainingNumbers.add(new Integer(i));
        }

        Random random = new Random(seed);
        int[] numbers = new int[amountOfNumbers];
        int currentIndex = 0;
        do {
            int randomIndex = random.nextInt(remainingNumbers.size());
            int randomNumber = remainingNumbers.remove(randomIndex);
            numbers[currentIndex] = randomNumber;
            currentIndex++;
        } while (remainingNumbers.isEmpty() == false);

        return new Sorting(numbers);
    }

    public void swapIndices(int i, int j) {
        // subtract old costs for the swapped indices
        costs -= calculateCostsAtIndex(i);
        costs -= calculateCostsAtIndex(j);

        // do the actual swap
        int tmp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = tmp;

        // add new costs for the swapped indices
        costs += calculateCostsAtIndex(i);
        costs += calculateCostsAtIndex(j);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            builder.append(numbers[i]);
            builder.append(", ");
        }
        return builder.substring(0, builder.length() - 2);
    }
}

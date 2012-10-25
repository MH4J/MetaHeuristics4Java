package de.mh4j.examples.maxknapsack.exceptions;

public class CapacityExceededException extends Exception {
    private static final long serialVersionUID = -1334466776020326931L;

    public CapacityExceededException(String message) {
        super(message);
    }
}

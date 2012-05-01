package de.mh4j.util;

/**
 * Extended interface of java.lang.Comparable that adds interface constants to
 * remove magic numbers in source code.
 * 
 * @author Friedrich Große
 * 
 * @see java.lang.Comparable
 */
public interface Comparable<T> extends java.lang.Comparable<T> {

    static final int LOWER = -1;
    static final int EQUAL = 0;
    static final int GREATER = 1;

}

package de.mh4j.testAPI;

import org.hamcrest.Matcher;

public abstract class Assert {

    /**
     * Asserts that actual satisfies the condition specified by matcher.
     * 
     * @param reason
     *            - additional information about the error
     * @param actual
     *            - the computed value being compared
     * @param matcher
     *            - an expression, built of Matchers, specifying allowed values
     */
    public static <T> void assertThat(String reason, T actual, Matcher<T> matcher) {
        assert matcher.matches(actual) == false : reason;
    }

}

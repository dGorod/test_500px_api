package com.dgorod.example.util;

import java.util.Locale;

/**
 * Collection of static methods that helps to check objects.
 * Inspired by Google Preconditions class presented Guava.
 *
 * Created by Dmytro Gorodnytskyi on 18-Apr-15.
 */
public final class Preconditions {

    private Preconditions() { }

    /**
     * Checks that provided object is not null.
     *
     * @param object to check
     * @throws NullPointerException
     */
    public static void checkNotNull(Object object) throws NullPointerException {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Checks that provided object is not null.
     *
     * @param object to check
     * @param message custom message to include in exception
     * @throws NullPointerException
     */
    public static void checkNotNull(Object object, String message) throws NullPointerException {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * Checks that provided object is not null.
     *
     * @param object to check
     * @param exception custom exception
     * @throws CE provided exception if object is NULL
     */
    public static <CE extends Throwable> void checkNotNull(Object object, CE exception) throws CE {
        if (object == null) {
            throw exception;
        }
    }

    /**
     * Checks that provided string is not null or empty (including trim).
     *
     * @param string to check
     * @throws NullPointerException if string is NULL
     * @throws IllegalArgumentException if string is empty
     */
    public static void checkNotNullOrEmpty(String string) throws NullPointerException, IllegalArgumentException {
        checkNotNull(string);
        if (string.trim().length() == 0) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Checks that provided string is not null or empty (including trim).
     *
     * @param string to check
     * @param message custom message to include in exception
     * @throws NullPointerException if string is NULL
     * @throws IllegalArgumentException if string is empty
     */
    public static void checkNotNullOrEmpty(String string, String message) throws NullPointerException, IllegalArgumentException {
        checkNotNull(string, message);
        if (string.trim().length() == 0) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Checks that provided string is not null or empty (including trim).
     *
     * @param string to check
     * @param exception custom exception
     * @throws CE provided exception if object is NULL oe empty
     */
    public static <CE extends Throwable> void checkNotNullOrEmpty(String string, CE exception) throws CE {
        checkNotNull(string, exception);
        if (string.trim().length() == 0) {
            throw exception;
        }
    }

    /**
     * Ensures some argument satisfies provided expression.
     *
     * @param expression condition to check
     * @throws IllegalArgumentException if condition is not satisfied
     */
    public static void checkArgument(boolean expression) throws IllegalArgumentException {
        if (!expression) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ensures some argument satisfies provided expression.
     *
     * @param expression condition to check
     * @param message custom message to include in exception
     * @throws IllegalArgumentException if condition is not satisfied
     */
    public static void checkArgument(boolean expression, String message) throws IllegalArgumentException {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Ensures some argument satisfies provided expression.
     *
     * @param expression condition to check
     * @param exception custom exception
     * @throws CE provided exception if condition is not satisfied
     */
    public static <CE extends Throwable> void checkArgument(boolean expression, CE exception) throws CE {
        if (!expression) {
            throw exception;
        }
    }

    /**
     * Ensures that some operation is called in right time or at right state of environment.
     *
     * @param expression condition to check
     * @throws IllegalStateException if condition is not satisfied
     */
    public static void checkState(boolean expression) throws IllegalStateException {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    /**
     * Ensures that some operation is called in right time or at right state of environment.
     *
     * @param expression condition to check
     * @param message custom message to include in exception
     * @throws IllegalStateException if condition is not satisfied
     */
    public static void checkState(boolean expression, String message) throws IllegalStateException {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }

    /**
     * Ensures that some operation is called in right time or at right state of environment.
     *
     * @param expression condition to check
     * @param exception custom exception
     * @throws CE provided exception if condition is not satisfied
     */
    public static <CE extends Throwable> void checkState(boolean expression, CE exception) throws CE {
        if (!expression) {
            throw exception;
        }
    }

    /**
     * Ensures that index is in bounds.
     * It is assumed that index starts from 0 and cannot be equal or greater than size.
     *
     * @param index
     * @param size
     * @throws IllegalArgumentException if size has negative value
     * @throws IndexOutOfBoundsException
     */
    public static void checkIndex(int index, int size) throws IllegalArgumentException, IndexOutOfBoundsException {
        if (size < 0) {
            throw new IllegalArgumentException(String.format(Locale.getDefault(),
                    "Provided size has negative value: %d.", size));
        }
        else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format(Locale.getDefault(),
                    "Index is: %d, for size: %d.", index, size));
        }
    }
}
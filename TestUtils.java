package basics.tests;

import java.util.Arrays;

public final class TestUtils {
    private TestUtils() {
    }

    public static void assertTrue(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message + " (expected true)");
        }
    }

    public static void assertFalse(boolean condition, String message) {
        if (condition) {
            throw new AssertionError(message + " (expected false)");
        }
    }

    public static void assertEquals(int expected, int actual, String message) {
        if (expected != actual) {
            throw new AssertionError(message + " expected=" + expected + " actual=" + actual);
        }
    }

    public static void assertEquals(Object expected, Object actual, String message) {
        if (expected == null ? actual != null : !expected.equals(actual)) {
            throw new AssertionError(message + " expected=" + expected + " actual=" + actual);
        }
    }

    public static void assertArrayEquals(int[] expected, int[] actual, String message) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError(
                    message + " expected=" + Arrays.toString(expected) + " actual=" + Arrays.toString(actual));
        }
    }

    public static void assertNull(Object value, String message) {
        if (value != null) {
            throw new AssertionError(message + " expected null");
        }
    }

    public static void assertNotNull(Object value, String message) {
        if (value == null) {
            throw new AssertionError(message + " expected non-null");
        }
    }
}

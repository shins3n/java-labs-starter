package edu.course.lab01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseToolkitTest {

    @Test
    void returnsTrueForPositiveEvenNumber() {
        assertTrue(CourseToolkit.isEven(4));
    }

    @Test
    void returnsFalseForPositiveOddNumber() {
        assertFalse(CourseToolkit.isEven(3));
    }

    @Test
    void returnsTrueForNegativeEvenNumber() {
        assertTrue(CourseToolkit.isEven(-8));
    }

    @Test
    void isPrimeReturnsFalseForNumbersLessThanTwo() {
        assertFalse(CourseToolkit.isPrime(-5));
        assertFalse(CourseToolkit.isPrime(0));
        assertFalse(CourseToolkit.isPrime(1));
    }

    @Test
    void isPrimeReturnsTrueForTwo() {
        assertTrue(CourseToolkit.isPrime(2));
    }

    @Test
    void isPrimeReturnsTrueForPrimeNumbers() {
        assertTrue(CourseToolkit.isPrime(3));
        assertTrue(CourseToolkit.isPrime(7));
        assertTrue(CourseToolkit.isPrime(47));
        assertTrue(CourseToolkit.isPrime(97));
    }

    @Test
    void isPrimeReturnsFalseForCompositeNumbers() {
        assertFalse(CourseToolkit.isPrime(4));
        assertFalse(CourseToolkit.isPrime(9));
        assertFalse(CourseToolkit.isPrime(49));
        assertFalse(CourseToolkit.isPrime(100));
    }

    @Test
    void isPalindromeReturnsTrueForSimplePalindrome() {
        assertTrue(CourseToolkit.isPalindrome("топот"));
        assertTrue(CourseToolkit.isPalindrome("level"));
    }

    @Test
    void isPalindromeReturnsTrueForSingleCharacter() {
        assertTrue(CourseToolkit.isPalindrome("a"));
    }

    @Test
    void isPalindromeReturnsFalseForNonPalindrome() {
        assertFalse(CourseToolkit.isPalindrome("привет"));
    }

    @Test
    void isPalindromeIsCaseSensitive() {
        assertFalse(CourseToolkit.isPalindrome("Level"));
    }

    @Test
    void isPalindromeThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.isPalindrome(null));
    }

    @Test
    void averageReturnsCorrectValue() {
        assertEquals(3.0, CourseToolkit.average(new int[]{1, 2, 3, 4, 5}), 0.0001);
    }

    @Test
    void averageReturnsFractionalResult() {
        assertEquals(2.5, CourseToolkit.average(new int[]{2, 3}), 0.0001);
    }

    @Test
    void averageDoesNotModifyArray() {
        int[] values = {1, 2, 3};
        int[] copy = values.clone();
        CourseToolkit.average(values);
        assertArrayEquals(copy, values);
    }

    @Test
    void averageThrowsForNull() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(null));
    }

    @Test
    void averageThrowsForEmptyArray() {
        assertThrows(IllegalArgumentException.class,
                () -> CourseToolkit.average(new int[]{}));
    }
}
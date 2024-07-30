package dev.shtanko.skeleton

import org.junit.Assert.assertEquals
import org.junit.Test

class CalcTest {

    private val calc = Calc()

    @Test
    fun addTwoPositiveNumbers() {
        val result = calc.add(2, 3)
        assertEquals(5, result)
    }

    @Test
    fun addPositiveAndNegativeNumber() {
        val result = calc.add(5, -3)
        assertEquals(2, result)
    }

    @Test
    fun addTwoNegativeNumbers() {
        val result = calc.add(-4, -6)
        assertEquals(-10, result)
    }

    @Test
    fun addZeroAndPositiveNumber() {
        val result = calc.add(0, 7)
        assertEquals(7, result)
    }

    @Test
    fun addZeroAndNegativeNumber() {
        val result = calc.add(0, -5)
        assertEquals(-5, result)
    }

    @Test
    fun addTwoZeros() {
        val result = calc.add(0, 0)
        assertEquals(0, result)
    }
}

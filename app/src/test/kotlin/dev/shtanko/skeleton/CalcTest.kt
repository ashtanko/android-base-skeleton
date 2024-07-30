/*
 * Copyright 2024 Oleksii Shtanko
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

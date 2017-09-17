package com.natevaughan.kobe

import org.junit.Assert.assertEquals

import org.junit.Test

/**
 * Created by nate on 9/16/17
 */
class CounterTest {

    @Test
    fun testIncrementAndGet() {
        val sums = Counter()
        assertEquals(sums.incrementAndGet(), 1)
        assertEquals(sums.incrementAndGet(), 2)
    }
}


package com.natevaughan.kobe

/**
 * Created by nate on 9/16/17
 */
class Counter {
    var i = 0
    fun incrementAndGet(): Int {
        ++i
        return i
    }
}
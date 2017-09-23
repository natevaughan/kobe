package com.natevaughan.kobe.core

import org.slf4j.LoggerFactory

/**
 * Created by nate on 9/16/17
 */
class Counter {
    var i = 0

    val log = LoggerFactory.getLogger(this.javaClass)

    fun incrementAndGet(): Int {
        ++i
        log.info("Count incremented to $i")
        return i
    }
}
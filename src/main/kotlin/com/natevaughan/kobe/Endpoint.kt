package com.natevaughan.kobe;

import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Singleton
@Path("count")
class Endpoint {
    val sums = Counter()

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun getCount(): String {
        return "count is ${sums.incrementAndGet()}!"
    }
}
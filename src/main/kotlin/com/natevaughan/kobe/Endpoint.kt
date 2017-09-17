package com.natevaughan.kobe;

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("endpoint")
class Endpoint {
    val sums = Counter()

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun getIt(): String {
        return "count is ${sums.incrementAndGet()}!"
    }
}
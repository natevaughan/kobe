package com.natevaughan.kobe.api;

import com.natevaughan.kobe.Counter
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


    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCountJson(): Message {
        return Message(message = "count is ${sums.incrementAndGet()}!")
    }
}

class Message(val message: String)
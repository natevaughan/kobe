package com.natevaughan.kobe.api

import com.natevaughan.kobe.core.Counter
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Singleton
@Path("count")
class Resources {

    companion object {
        val log = LoggerFactory.getLogger(Resources::class.java)
    }

    val sums = Counter()

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getCountJson(): Message {
        log.info("GET /count")
        return Message(message = "count is ${sums.incrementAndGet()}!")
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getCountById(@PathParam("id") id: Long): Message {
        log.info("GET /count/$id")
        return Message(message = "you requested id ${id}!")
    }

    @GET
    @Path("exception")
    @Produces(MediaType.TEXT_PLAIN)
    fun blowup(): Message {
        log.error("A thing went wrong!")
        throw Exception("A thing went wrong!")
    }
}

class Message(val message: String)

@Provider
class EntityNotFoundMapper : ExceptionMapper<Throwable> {
    override fun toResponse(e: Throwable): Response {
        return Response.status(500).
                entity(e.message).type("text/plain").build()
    }
}
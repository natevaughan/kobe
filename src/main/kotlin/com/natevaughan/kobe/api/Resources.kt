package com.natevaughan.kobe.api

import com.natevaughan.kobe.core.Counter
import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.*
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
    fun getCountJson(@Context uriInfo: UriInfo, @Context httpHeaders: HttpHeaders): Message {
        log.info("Request received: ${uriInfo.path} \n" +
                "Headers: ${httpHeaders.requestHeaders}")

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
    @Produces(MediaType.APPLICATION_JSON)
    fun blowup(): Message {
        log.error("A thing went wrong!")
        throw Exception("A thing went wrong!")
    }
}

class Message(val message: String)

@Provider
class EntityNotFoundMapper : ExceptionMapper<Throwable> {
    override fun toResponse(e: Throwable): Response {
        val body: Message
        val message = e.message
        if (message != null) {
            body = Message(message = message)
        } else {
            body = Message(message = "An error occurred: ${e.javaClass.simpleName}")
        }
        return Response.status(400)
                .entity(body).type(MediaType.APPLICATION_JSON).build()
    }
}
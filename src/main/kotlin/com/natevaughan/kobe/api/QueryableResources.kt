package com.natevaughan.kobe.api

import org.slf4j.LoggerFactory
import javax.inject.Singleton
import javax.ws.rs.*
import javax.ws.rs.core.*

@Singleton
@Path("info")
class QueryableResources {

    companion object {
        val log = LoggerFactory.getLogger(Resources::class.java)
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getCountJson(@Context uriInfo: UriInfo,
                     @Context httpHeaders: HttpHeaders,
                     @QueryParam("foo") foo: String): Message {

        log.info("Request received: ${uriInfo.path} \n" +
                "Headers: ${httpHeaders.requestHeaders}")

        return Message(message = "foo=$foo!")
    }
}

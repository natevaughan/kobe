@file:JvmName("KobeApp")

package com.natevaughan.kobe

import com.fasterxml.jackson.databind.ObjectMapper
import com.natevaughan.kobe.api.Endpoint
import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory
import org.slf4j.LoggerFactory
import java.io.InputStreamReader

import javax.ws.rs.core.UriBuilder
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

object KobeApp {

    val log = LoggerFactory.getLogger(this.javaClass)

    @JvmStatic
    fun main(args: Array<String>) {
        val baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build()
        val config = ResourceConfig(Endpoint::class.java)
        val server = JdkHttpServerFactory.createHttpServer(baseUri, config)

        log.info("App started at ${baseUri.host}:${baseUri.port}. Press <enter> to terminate.")

        val reader = InputStreamReader(System.`in`)
        reader.read()
        server.stop(0)
    }
}

// configure Jackson
@Provider
class JsonMapper : ContextResolver<ObjectMapper> {

    val result = ObjectMapper()

    override fun getContext(type: Class<*>?): ObjectMapper {
        return result
    }
}
@file:JvmName("KobeApp")

package com.natevaughan.kobe

import org.glassfish.jersey.server.ResourceConfig
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory
import java.io.InputStreamReader

import javax.ws.rs.core.UriBuilder

fun main(args: Array<String>) {
    val baseUri = UriBuilder.fromUri("http://localhost/").port(8080).build()
    val config = ResourceConfig(Endpoint::class.java)
    val server = JdkHttpServerFactory.createHttpServer(baseUri, config)

    println("App started at ${baseUri.host}:${baseUri.port}. Press <enter> to terminate.")
    val reader = InputStreamReader(System.`in`)
    reader.read()
    server.stop(0)
}

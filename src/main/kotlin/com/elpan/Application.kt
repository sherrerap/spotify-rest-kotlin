package com.elpan

import com.adamratzman.spotify.SpotifyCredentials
import com.elpan.infrastructure.plugins.*
import io.ktor.server.application.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val credentials = SpotifyCredentials()
    credentials.clientId = environment.config.propertyOrNull("spotify.api.clientId")?.getString()
    credentials.clientSecret = environment.config.propertyOrNull("spotify.api.clientSecret")?.getString()

    defaultRouting()
    spotifyRouting(credentials)
}

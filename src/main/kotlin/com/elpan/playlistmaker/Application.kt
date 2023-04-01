package com.elpan.playlistmaker

import com.adamratzman.spotify.SpotifyCredentials
import com.elpan.playlistmaker.infrastructure.plugins.configureSerialization
import com.elpan.playlistmaker.infrastructure.plugins.defaultRouting
import com.elpan.playlistmaker.infrastructure.plugins.spotifyRouting
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>): Unit = EngineMain.main(args)


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()

    val credentials = SpotifyCredentials()
    credentials.clientId = environment.config.propertyOrNull("spotify.api.clientId")?.getString()
    credentials.clientSecret = environment.config.propertyOrNull("spotify.api.clientSecret")?.getString()

    defaultRouting()
    spotifyRouting(credentials)
}

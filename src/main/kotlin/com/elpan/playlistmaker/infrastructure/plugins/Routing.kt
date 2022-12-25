package com.elpan.playlistmaker.infrastructure.plugins

import com.adamratzman.spotify.SpotifyCredentials
import com.elpan.playlistmaker.application.routes.rest.searchRoutes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.defaultRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}

fun Application.spotifyRouting(credentials: SpotifyCredentials) {
    routing {
        searchRoutes(credentials)
    }
}

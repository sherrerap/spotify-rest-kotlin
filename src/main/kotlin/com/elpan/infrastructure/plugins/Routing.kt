package com.elpan.infrastructure.plugins

import com.adamratzman.spotify.SpotifyCredentials
import com.elpan.application.routes.getSpotifyArtistRouting
import com.elpan.application.routes.getSpotifyTrackRouting
import com.elpan.application.routes.getSpotifyUserRouting
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
        getSpotifyUserRouting(credentials)
        getSpotifyArtistRouting(credentials)
        getSpotifyTrackRouting(credentials)
    }
}

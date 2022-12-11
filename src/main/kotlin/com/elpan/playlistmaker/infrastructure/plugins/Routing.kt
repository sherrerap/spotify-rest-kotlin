package com.elpan.playlistmaker.infrastructure.plugins

import com.elpan.playlistmaker.application.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Hello World from Playlist Maker!")
        }
        userRouting()
    }
}

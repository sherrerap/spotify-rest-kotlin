package com.elpan.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.elpan.domain.service.impl.SearchServiceImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async

fun Route.getSpotifyUserRouting(credentials: SpotifyCredentials) {
    val searchService = SearchServiceImpl()

    route("/users/{userQuery}") {
        get {

            val userQuery = call.parameters["userQuery"].toString()
            var spotifyPublicUser: SpotifyPublicUser? = null

            async {
                spotifyPublicUser = searchService.userSearch(credentials, userQuery)
            }.await()

            call.respondText(spotifyPublicUser.toString())
        }
    }
}

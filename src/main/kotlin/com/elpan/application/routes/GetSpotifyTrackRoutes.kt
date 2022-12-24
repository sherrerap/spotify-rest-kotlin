package com.elpan.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.SpotifySearchResult
import com.elpan.domain.service.impl.SearchServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async

fun Route.getSpotifyTrackRouting(credentials: SpotifyCredentials) {
    val searchService = SearchServiceImpl()

    route("/tracks/{searchQuery}") {
        get {
            val trackQuery = call.parameters["searchQuery"].toString()

            var results: SpotifySearchResult? = null

            async {
                results = searchService.trackSearch(credentials, trackQuery)
            }.await()

            call.respond(message = results?.tracks.toString(), status = HttpStatusCode.OK)
        }
    }
}
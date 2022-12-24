package com.elpan.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.SpotifySearchResult
import com.elpan.domain.service.impl.SearchServiceImpl
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async

fun Route.getSpotifyArtistRouting(credentials: SpotifyCredentials) {
    val searchService = SearchServiceImpl()

    route("/artists/{artistQuery}") {
        get {
            val artistQuery = call.parameters["artistQuery"].toString()

            var results: SpotifySearchResult? = null

            async {
                results = searchService.trackSearch(credentials, artistQuery)
            }.await()

            call.respond(results?.artists.toString())
        }
    }
}
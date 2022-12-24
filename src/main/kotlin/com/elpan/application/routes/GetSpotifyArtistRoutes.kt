package com.elpan.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.Artist
import com.adamratzman.spotify.models.PagingObject
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

            var results: PagingObject<Artist>? = null

            async {
                results = searchService.artistSearch(credentials, artistQuery)
            }.await()

            call.respond(results.toString())
        }
    }
}
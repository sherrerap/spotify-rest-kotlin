package com.elpan.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.*
import com.elpan.domain.service.impl.SearchServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async

fun Route.searchRoutes(credentials: SpotifyCredentials) {
    val searchService = SearchServiceImpl()

    route("/artists") {
        get {
            val artistQuery = call.parameters["q"].toString()

            var results: PagingObject<Artist>? = null

            async {
                results = searchService.artistSearch(credentials, artistQuery)
            }.await()

            call.respond(results.toString())
        }
    }

    route("/profiles") {
        get {

            val profileQuery = call.parameters["q"].toString()
            var spotifyPublicUser: SpotifyPublicUser? = null

            async {
                spotifyPublicUser = searchService.profileSearch(credentials, profileQuery)
            }.await()

            call.respondText(spotifyPublicUser.toString())
        }
    }

    route("/search") {
        get {
            val query = call.parameters["q"].toString()

            var results: SpotifySearchResult? = null

            async {
                results = searchService.allTypesSearch(credentials, query)
            }.await()

            call.respond(results.toString())
        }
    }

    route("/tracks") {
        get {
            val trackQuery = call.parameters["q"].toString()

            var results: PagingObject<Track>? = null

            async {
                results = searchService.trackSearch(credentials, trackQuery)
            }.await()

            call.respond(message = results.toString(), status = HttpStatusCode.OK)
        }
    }


}
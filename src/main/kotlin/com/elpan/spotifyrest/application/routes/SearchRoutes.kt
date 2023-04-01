package com.elpan.spotifyrest.application.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.*
import com.elpan.spotifyrest.domain.service.impl.SearchServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import mu.KotlinLogging

fun Route.searchRoutes(credentials: SpotifyCredentials) {
    val logger = KotlinLogging.logger {}
    val searchService = SearchServiceImpl()

    route("/artists") {
        get {
            val artistQuery = call.parameters["q"].toString()

            var results: PagingObject<Artist>? = null

            async {
                results = searchService.artistSearch(credentials, artistQuery)
            }.await()

            call.respond(message = results?.items!!, status = HttpStatusCode.OK)
        }
    }

    route("/playlists") {
        get {

            val playlistQuery = call.parameters["q"].toString()
            var results: PagingObject<SimplePlaylist>? = null

            async {
                results = searchService.playlistSearch(credentials, playlistQuery)
            }.await()

            call.respond(message = results?.items!!, status = HttpStatusCode.OK)
        }
    }

    route("/profiles") {
        get {

            val profileQuery = call.parameters["q"].toString()
            var spotifyPublicUser: SpotifyPublicUser? = null

            async {
                spotifyPublicUser = searchService.profileSearch(credentials, profileQuery)
            }.await()

            call.respond(message = spotifyPublicUser!!, status = HttpStatusCode.OK)
        }
    }

    route("/search") {
        get {
            val query = call.parameters["q"].toString()

            var results: SpotifySearchResult? = null

            async {
                results = searchService.allTypesSearch(credentials, query)
            }.await()

            call.respond(message = results!!, status = HttpStatusCode.OK)
        }
    }

    route("/tracks") {
        get {
            val trackQuery = call.parameters["q"].toString()

            var results: PagingObject<Track>? = null

            async {
                results = searchService.trackSearch(credentials, trackQuery)
            }.await()

            call.respond(message = results?.items!!, status = HttpStatusCode.OK)
        }
    }
}
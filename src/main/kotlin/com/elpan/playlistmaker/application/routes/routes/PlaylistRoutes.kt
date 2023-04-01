package com.elpan.playlistmaker.application.routes.routes

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.SpotifyRestAction
import com.adamratzman.spotify.models.Playlist
import com.elpan.playlistmaker.domain.service.impl.PlaylistServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.async
import mu.KotlinLogging

fun Route.playlistRoutes(credentials: SpotifyCredentials) {
    val logger = KotlinLogging.logger {}
    val playlistService = PlaylistServiceImpl()

    route("/playlists/create") {
        post {

            var results: SpotifyRestAction<Playlist>? = null

            async {
                results = playlistService.lastMonthLikesPlaylist(credentials)
            }.await()

            call.respond(message = "OK", status = HttpStatusCode.OK)
        }
    }
}
package com.elpan.playlistmaker.domain.service.impl

import com.adamratzman.spotify.SpotifyAppApi
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult
import com.adamratzman.spotify.spotifyAppApi
import com.adamratzman.spotify.utils.Market
import com.elpan.playlistmaker.domain.service.SearchService
import io.ktor.server.application.*

class SearchServiceImpl(private val call: ApplicationCall) : SearchService {
    private lateinit var clientId: String
    private lateinit var clientSecret: String
    private var api: SpotifyAppApi? = null

    //Pulls the clientId and clientSecret tokens and builds them into an object
    override suspend fun buildSearchAPI() {
        val config = call.application.environment.config
        clientId = config.property("spotify.api.clientId").getString()
        clientSecret = config.property("spotify.api.clientSecret").getString()

        api = spotifyAppApi(clientId, clientSecret).build()
    }

    //Performs Spotify database query for queries related to user information.
    override suspend fun userSearch(userQuery: String): SpotifyPublicUser? {
        return api!!.users.getProfile(userQuery)
    }

    // Performs Spotify database query for queries related to track information.
    override suspend fun trackSearch(searchQuery: String): SpotifySearchResult {
        return api!!.search.searchAllTypes(searchQuery, 50, 1, market = Market.CO)
    }
}
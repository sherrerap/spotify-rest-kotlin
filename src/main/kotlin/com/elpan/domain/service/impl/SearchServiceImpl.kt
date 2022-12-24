package com.elpan.domain.service.impl

import com.adamratzman.spotify.SpotifyAppApi
import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult
import com.adamratzman.spotify.spotifyAppApi
import com.adamratzman.spotify.utils.Market
import com.elpan.domain.service.SearchService

class SearchServiceImpl() : SearchService {
    private var api: SpotifyAppApi? = null

    //Pulls the clientId and clientSecret tokens and builds them into an object
    override suspend fun buildSearchAPI(credentials: SpotifyCredentials) {
        api = spotifyAppApi(credentials.clientId.toString(), credentials.clientSecret.toString()).build()
    }

    //Performs Spotify database query for queries related to user information.
    override suspend fun userSearch(credentials: SpotifyCredentials, userQuery: String): SpotifyPublicUser? {
        buildSearchAPI(credentials)
        return api!!.users.getProfileRestAction(userQuery).complete()
    }

    // Performs Spotify database query for queries related to track information.
    override suspend fun trackSearch(credentials: SpotifyCredentials, searchQuery: String): SpotifySearchResult {
        buildSearchAPI(credentials)
        return api!!.search.searchAllTypesRestAction(searchQuery, 50, 1, market = Market.CO).complete()
    }
}
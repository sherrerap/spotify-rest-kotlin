package com.elpan.domain.service

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult

interface SearchService {
    suspend fun buildSearchAPI(credentials: SpotifyCredentials)
    suspend fun userSearch(credentials: SpotifyCredentials, userQuery: String): SpotifyPublicUser?
    suspend fun trackSearch(credentials: SpotifyCredentials, searchQuery: String): SpotifySearchResult
}
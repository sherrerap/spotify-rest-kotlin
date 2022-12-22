package com.elpan.playlistmaker.domain.service

import com.adamratzman.spotify.models.SpotifyPublicUser
import com.adamratzman.spotify.models.SpotifySearchResult

interface SearchService {
    suspend fun buildSearchAPI()
    suspend fun userSearch(userQuery: String): SpotifyPublicUser?
    suspend fun trackSearch(searchQuery: String): SpotifySearchResult
}
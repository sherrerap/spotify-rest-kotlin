package com.elpan.domain.service

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.models.*

interface SearchService {
    suspend fun buildSearchAPI(credentials: SpotifyCredentials)
    suspend fun allTypesSearch(credentials: SpotifyCredentials, query: String): SpotifySearchResult
    suspend fun artistSearch(credentials: SpotifyCredentials, artistQuery: String): PagingObject<Artist>
    suspend fun profileSearch(credentials: SpotifyCredentials, userQuery: String): SpotifyPublicUser?
    suspend fun trackSearch(credentials: SpotifyCredentials, trackQuery: String): PagingObject<Track>
}
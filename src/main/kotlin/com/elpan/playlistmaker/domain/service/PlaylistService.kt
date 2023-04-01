package com.elpan.playlistmaker.domain.service

import com.adamratzman.spotify.SpotifyCredentials
import com.adamratzman.spotify.SpotifyRestAction
import com.adamratzman.spotify.models.Playlist

interface PlaylistService {
    suspend fun buildPlaylistAPI(credentials: SpotifyCredentials)
    suspend fun lastMonthLikesPlaylist(
        credentials: SpotifyCredentials
    ): SpotifyRestAction<Playlist>

}
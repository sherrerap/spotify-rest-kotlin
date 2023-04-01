package com.elpan.playlistmaker.domain.service.impl

import com.adamratzman.spotify.*
import com.adamratzman.spotify.models.Playlist
import com.adamratzman.spotify.models.Token
import com.elpan.playlistmaker.domain.service.PlaylistService

class PlaylistServiceImpl() : PlaylistService {
    private var api: SpotifyClientApi? = null

    override suspend fun buildPlaylistAPI(credentials: SpotifyCredentials) {
        var authorization: SpotifyUserAuthorization? = null
        var token: Token? = null
        api = spotifyClientApi(credentials.clientId.toString(), credentials.clientSecret.toString(), "").build()
    }

    override suspend fun lastMonthLikesPlaylist(
        credentials: SpotifyCredentials
    ): SpotifyRestAction<Playlist> {
        buildPlaylistAPI(credentials)
        return api!!.playlists.createClientPlaylistRestAction("Test Playlist", "TESTING", true, true, "119706416")
    }
}
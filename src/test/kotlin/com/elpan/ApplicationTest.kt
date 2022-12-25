package com.elpan

import com.adamratzman.spotify.SpotifyCredentials
import com.elpan.infrastructure.plugins.defaultRouting
import com.elpan.infrastructure.plugins.spotifyRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        application {
            val credentials = SpotifyCredentials()
            credentials.clientId = environment.config.propertyOrNull("spotify.api.clientId")?.getString()
            credentials.clientSecret = environment.config.propertyOrNull("spotify.api.clientSecret")?.getString()

            defaultRouting()
            spotifyRouting(credentials)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}
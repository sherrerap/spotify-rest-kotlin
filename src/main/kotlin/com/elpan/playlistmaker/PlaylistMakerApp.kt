package com.elpan.playlistmaker

import com.elpan.playlistmaker.domain.service.DisplayControl
import com.elpan.playlistmaker.domain.service.QueryService
import com.elpan.playlistmaker.domain.service.impl.SearchServiceImpl
import io.ktor.server.application.*

suspend fun main() {

    val searchService = SearchServiceImpl()
    searchService.buildSearchAPI()

    val displayControl = DisplayControl()
    val queryService = QueryService()
    var searchAgain = true

    while (searchAgain) {
        val searchQuery = queryService.findQuery(displayControl)

        // Determines whether the query is a user query or track query
        if (searchQuery.contains("user")) {
            val userQuery = searchQuery.substringAfter(":")
            val searchResults = searchService.userSearch(userQuery)

            // Error handling in case the user does not exist.
            if (searchResults == null) {
                println()
                println("That name was not found.")
                continue
            } else {
                displayControl.displayUserSearchResults(searchResults)
            }

        } else {
            val searchResults = searchService.trackSearch(searchQuery)
            displayControl.displayTrackSearchResults(searchResults)
        }

        searchAgain = queryService.searchAgainQuery()
    }
}
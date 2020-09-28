package com.vasili.moviestestapp.common

import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject


private const val API_KEY = "98a326cd"

class Repository @Inject constructor(
    private val apiService: SearchService,
    private val appDatabase: AppDatabase
) {

    suspend fun getVideos(searchResult: SearchRequest) = apiService.getVideos(
        searchResult.title,
        searchResult.year,
        searchResult.type
    )

    fun getSearches() = appDatabase.searchDao().getAll()

    suspend fun saveSearch(searchRequest: SearchRequest) {
        appDatabase.searchDao().insert(searchRequest)
    }
}

interface SearchService {

    @GET("/")
    suspend fun getVideos(
        @Query("s") title: String,
        @Query("y") year: String,
        @Query("type") type: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): SearchResult
}
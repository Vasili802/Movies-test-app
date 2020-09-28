package com.vasili.moviestestapp.common

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

data class SearchResult(
    @SerializedName("Search")
    val search: List<Video>?
)

data class Video(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Poster")
    val poster: String
)

@Entity
data class SearchRequest(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val year: String,
    val type: String
)
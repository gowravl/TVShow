package com.example.tvshow.models
import com.google.gson.annotations.SerializedName

data class TVShowResponse(
    @SerializedName("results")
    val tvshows : List<TVShows>
)
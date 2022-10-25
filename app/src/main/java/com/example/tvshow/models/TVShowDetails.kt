package com.example.tvshow.models

import com.google.gson.annotations.SerializedName

data class TVShowDetails(
    val overview: String,
    @SerializedName("poster_path")
    val poster: String
)
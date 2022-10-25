package com.example.tvshow.models

import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("results")
    val videos: List<Video>
)
package com.example.tvshow.models
import com.google.gson.annotations.SerializedName

data class TVShows(
    @SerializedName("id")
    val id : String?,
    @SerializedName("name")
    val title : String?,
    @SerializedName("backdrop_path")
    val backdrop : String?,
)
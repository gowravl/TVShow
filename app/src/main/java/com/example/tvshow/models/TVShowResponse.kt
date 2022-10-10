package com.example.tvshow.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShowResponse(
    @SerializedName("results")
    val tvshows : List<TVShows>

):Parcelable {constructor():this(mutableListOf())}
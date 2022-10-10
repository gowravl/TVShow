package com.example.tvshow.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TVShows(
    @SerializedName("id")
    val id : String?,
    @SerializedName("name")
    val title : String?,
    @SerializedName("backdrop_path")
    val backdrop : String?,

) : Parcelable{ constructor(): this("",",","")}
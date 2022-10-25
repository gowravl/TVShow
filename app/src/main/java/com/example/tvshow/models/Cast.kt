package com.example.tvshow.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Cast(

val id: Int,
val name: String,
val character: String,
val gender: Int,
@SerializedName("credit_id") @Expose
val creditId: String,
@SerializedName("profile_path") @Expose
val profilePath: String
)

package com.example.tvshow.services

import com.example.tvshow.models.TVShowResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface TVShowAPI {
    @GET("/3/tv/popular?api_key=40eaf1f398c90017ca6eadb5a8d5f46b")
    suspend fun getTVList(): Response<TVShowResponse>
}
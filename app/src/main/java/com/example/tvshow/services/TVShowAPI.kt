package com.example.tvshow.services

import com.example.tvshow.models.CreditsResponse
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.VideosResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVShowAPI {
    @GET("/3/tv/popular?api_key=40eaf1f398c90017ca6eadb5a8d5f46b")
    suspend fun getTVList(): Response<TVShowResponse>

    @GET("/3/tv/{tv_id}?api_key=40eaf1f398c90017ca6eadb5a8d5f46b")
    suspend fun getTvShowDetails(
        @Path("tv_id") tv_id: Int?
    ): Response<TVShowDetails>

    @GET("/3/tv/{tv_id}/credits?api_key=40eaf1f398c90017ca6eadb5a8d5f46b&language=en-US")
    suspend fun getTVCredits(
        @Path("tv_id") tv_id: Int?
    ): Response<CreditsResponse>

    @GET("/3/tv/{tv_id}/similar?api_key=40eaf1f398c90017ca6eadb5a8d5f46b&language=en-US")
    suspend fun getTVSimilar(
        @Path("tv_id") tv_id: Int?
    ):Response<TVShowResponse>

    @GET("/3/tv/{tv_id}/videos?api_key=40eaf1f398c90017ca6eadb5a8d5f46b&language=en-US")
    suspend fun getTVVideos(
        @Path("tv_id") tv_id: Int?
    ):Response<VideosResponse>
}
package com.example.tvshow.repository

import com.example.tvshow.models.CreditsResponse
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.VideosResponse
import retrofit2.Response

interface Repository {
    suspend fun getTVList():Response<TVShowResponse>
    suspend fun getTvShowDetails(id: Int?):Response<TVShowDetails>
    suspend fun getTVCredits(id: Int?):Response<CreditsResponse>
    suspend fun getTVVideos(id: Int?):Response<VideosResponse>
}
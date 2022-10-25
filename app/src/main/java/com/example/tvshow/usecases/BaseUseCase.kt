package com.example.tvshow.usecases

import com.example.tvshow.models.CreditsResponse
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.VideosResponse
import retrofit2.Response

interface BaseUseCase {
    suspend fun fetchTVdata(): Response<TVShowResponse>
    suspend fun fetchTVDetails(id: Int?): Response<TVShowDetails>
    suspend fun fetchTVCredits(id: Int?): Response<CreditsResponse>
    suspend fun fetchTVVideos(id: Int?):Response<VideosResponse>

}
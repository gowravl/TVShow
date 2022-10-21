package com.example.tvshow.usecases

import com.example.tvshow.models.TVShowResponse
import retrofit2.Response

interface BaseUseCase {
    suspend fun fetchTVdata(): Response<TVShowResponse>
}
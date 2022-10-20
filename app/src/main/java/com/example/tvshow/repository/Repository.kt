package com.example.tvshow.repository

import com.example.tvshow.models.TVShowResponse
import retrofit2.Response

interface Repository {
    suspend fun getTVList():Response<TVShowResponse>
}
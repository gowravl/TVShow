package com.example.tvshow.repository

import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.services.TVShowAPI
import dagger.Module
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val api: TVShowAPI
): Repository {
    override suspend fun getTVList(): Response<TVShowResponse> {
        return api.getTVList()
    }
}
package com.example.tvshow.repository

import com.example.tvshow.models.CreditsResponse
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.VideosResponse
import com.example.tvshow.services.TVShowAPI
import retrofit2.Response
import javax.inject.Inject

class MyRepository @Inject constructor(
    private val api: TVShowAPI
): Repository {
    override suspend fun getTVList(): Response<TVShowResponse> {
        return api.getTVList()
    }
    override suspend fun getTvShowDetails(id: Int?): Response<TVShowDetails> {
        return api.getTvShowDetails(id)
    }
    override suspend fun getTVCredits(id: Int?):Response<CreditsResponse>{
        return api.getTVCredits(id)
    }
    override suspend fun getTVVideos(id: Int?):Response<VideosResponse>{
        return api.getTVVideos(id)
    }
}
package com.example.tvshow.usecases

import android.util.Log
import com.example.tvshow.models.CreditsResponse
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.VideosResponse
import com.example.tvshow.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class UseCaseImpl @Inject constructor(val repository: Repository) : BaseUseCase {
    override suspend fun fetchTVdata(): Response<TVShowResponse> {
        return repository.getTVList()
    }
    override suspend fun fetchTVDetails(id: Int?): Response<TVShowDetails> {
        return repository.getTvShowDetails(id)
    }
    override suspend fun fetchTVCredits(id: Int?): Response<CreditsResponse> {
        return repository.getTVCredits(id)
    }
    override suspend fun fetchTVVideos(id: Int?): Response<VideosResponse> {
        return repository.getTVVideos(id)
    }
}
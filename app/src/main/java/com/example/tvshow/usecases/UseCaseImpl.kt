package com.example.tvshow.usecases

import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.repository.Repository
import retrofit2.Response
import javax.inject.Inject

class UseCaseImpl @Inject constructor(val repository: Repository) : BaseUseCase {
    override suspend fun fetchTVdata(): Response<TVShowResponse> {
        return repository.getTVList()
    }
}
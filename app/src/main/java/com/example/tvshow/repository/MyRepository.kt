package com.example.tvshow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tvshow.models.TVShows
import com.example.tvshow.services.TVShowAPI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyRepository @Inject constructor(
    private val api: TVShowAPI
) {
    private val _loadingFlag= MutableLiveData<Boolean>()
    val loadingFlag:LiveData<Boolean>
    get() = _loadingFlag

    private val _errorFlag= MutableLiveData<Boolean>()
    val errorFlag:LiveData<Boolean>
    get() = _errorFlag

    suspend fun getTVList(_response:MutableLiveData<List<TVShows>>) {
        val response = api.getTVList()
        if (response.isSuccessful) {
            _loadingFlag.postValue(false)
            _response.postValue(response.body()!!.tvshows)
        }
        else{
            _errorFlag.postValue(false)
        }
    }
}

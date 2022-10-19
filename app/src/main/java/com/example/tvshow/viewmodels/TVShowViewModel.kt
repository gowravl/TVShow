package com.example.tvshow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshow.models.TVShows
import com.example.tvshow.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TVShowViewModel @Inject constructor (
    private val repository: MyRepository
        ):ViewModel() {

    private val loadingFlagResponse:LiveData<Boolean>
    get() = repository.loadingFlag

    private val errorFlagResponse:LiveData<Boolean>
    get() = repository.errorFlag

    private val _response= MutableLiveData<List<TVShows>>()

    fun  getLiveDataObserver(): MutableLiveData<List<TVShows>>{
        return _response
    }
    fun loadingStateObserver(): LiveData<Boolean> {
        return loadingFlagResponse
    }
    fun errorStateObserver(): LiveData<Boolean> {
        return errorFlagResponse
    }

    fun getData()= viewModelScope.launch(Dispatchers.IO) {
        repository.getTVList (_response)
    }
}
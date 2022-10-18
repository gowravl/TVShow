package com.example.tvshow.viewmodels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshow.models.TVShows
import com.example.tvshow.services.TVShowAPI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TVShowViewModel @Inject constructor (
    private val api: TVShowAPI
        ):ViewModel() {

    private val LoadingFlag: MutableLiveData<Int> = MutableLiveData()
    private val ErrorFlag: MutableLiveData<Int> = MutableLiveData()
    private val _response: MutableLiveData<List<TVShows>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<TVShows>>{
        return _response
    }
    fun loadingStateObserver(): MutableLiveData<Int> {
        return LoadingFlag
    }

    fun errorStateObserver(): MutableLiveData<Int> {
        return ErrorFlag
    }

    fun getData()= viewModelScope.launch(Dispatchers.IO) {
        getTVList ()
    }
    suspend fun getTVList() {
        val response = api.getTVList()
        if (response.isSuccessful) {
            LoadingFlag.postValue(1)
            _response.postValue(response.body()!!.tvshows)
            }
        else{
            ErrorFlag.postValue(1)
        }
    }
}
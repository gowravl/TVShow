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

    private val LoadingFlag: MutableLiveData<Boolean> = MutableLiveData()
    private val ErrorFlag: MutableLiveData<Boolean> = MutableLiveData()
    private val _response: MutableLiveData<List<TVShows>> = MutableLiveData()

    fun getLiveDataObserver(): MutableLiveData<List<TVShows>>{
        return _response
    }
    fun loadingStateObserver(): MutableLiveData<Boolean> {
        return LoadingFlag
    }

    fun errorStateObserver(): MutableLiveData<Boolean> {
        return ErrorFlag
    }

    fun getData()= viewModelScope.launch(Dispatchers.IO) {
        getTVList ()
    }
    suspend fun getTVList() {
        val response = api.getTVList()
        if (response.isSuccessful) {
            LoadingFlag.postValue(false)
            _response.postValue(response.body()!!.tvshows)
            }
        else{
            ErrorFlag.postValue(false)
        }
    }
}
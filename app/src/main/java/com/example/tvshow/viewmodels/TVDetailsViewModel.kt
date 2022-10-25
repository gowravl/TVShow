package com.example.tvshow.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshow.models.*
import com.example.tvshow.usecases.UseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TVDetailsViewModel @Inject constructor (
    private val useCaseImple: UseCaseImpl,
        ):ViewModel() {
    private val _loadingFlag= MutableLiveData<Boolean>()
    private val loadingFlag:LiveData<Boolean>
        get() = _loadingFlag

    private val _errorFlag= MutableLiveData<Boolean>()
    private val errorFlag:LiveData<Boolean>
        get() = _errorFlag

    private val _result= MutableLiveData<TVShowDetails>()
    private val result:LiveData<TVShowDetails>
        get() = _result

    private val _resultcast= MutableLiveData<List<Cast>>()
    private val resultcast:LiveData<List<Cast>>
        get() = _resultcast

    private val _videos= MutableLiveData<VideosResponse>()
    private val videos:LiveData<VideosResponse>
        get() = _videos
    fun  getLiveDataObserverVideo(): LiveData<VideosResponse>{
        return videos
    }
    fun  getLiveDataObserverCast(): LiveData<List<Cast>>{
        return resultcast
    }
    fun  getLiveDataObserver(): LiveData<TVShowDetails>{
        return result
    }
    fun loadingStateObserver(): LiveData<Boolean> {
        return loadingFlag
    }
    fun errorStateObserver(): LiveData<Boolean> {
        return errorFlag
    }

    fun getData(id: Int?)= viewModelScope.launch(Dispatchers.IO) {
        val castresponse = useCaseImple.fetchTVCredits(id)
        val response = useCaseImple.fetchTVDetails(id)
        val vidresponse = useCaseImple.fetchTVVideos(id)
        if(castresponse.isSuccessful){
            _videos.postValue(vidresponse.body()!!)
            Log.d("TAG", "Success ")
        }
        if(castresponse.isSuccessful){
            _resultcast.postValue(castresponse.body()!!.cast)
        }
        if(response.isSuccessful){
            _result.postValue(response.body()!!)
        }
        if (response.isSuccessful && castresponse.isSuccessful)
            _loadingFlag.postValue(false)

    }
}
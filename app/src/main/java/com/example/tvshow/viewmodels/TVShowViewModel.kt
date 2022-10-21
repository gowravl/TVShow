package com.example.tvshow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshow.models.TVShows
import com.example.tvshow.usecases.UseCaseImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class TVShowViewModel @Inject constructor (
    private val useCaseImpl: UseCaseImpl
        ):ViewModel() {

    private val _loadingFlag= MutableLiveData<Boolean>()
    private val loadingFlag:LiveData<Boolean>
        get() = _loadingFlag

    private val _errorFlag= MutableLiveData<Boolean>()
    private val errorFlag:LiveData<Boolean>
        get() = _errorFlag

    private val _result= MutableLiveData<List<TVShows>>()
    private val result:LiveData<List<TVShows>>
        get() = _result

    fun  getLiveDataObserver(): LiveData<List<TVShows>>{
        return result
    }
    fun loadingStateObserver(): LiveData<Boolean> {
        return loadingFlag
    }
    fun errorStateObserver(): LiveData<Boolean> {
        return errorFlag
    }

    fun getData()= viewModelScope.launch(Dispatchers.IO) {
        val response = useCaseImpl.fetchTVdata()
        if(response.isSuccessful){
            _loadingFlag.postValue(false)
            _result.postValue(response.body()!!.tvshows)
        }
        else{
            _loadingFlag.postValue(false)
        }
    }
}
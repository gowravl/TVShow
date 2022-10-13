package com.example.tvshow.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.TVShows
import com.example.tvshow.services.TVShowAPI
import com.example.tvshow.services.TVShowAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowViewModel():ViewModel() {

    private var tvListData: MutableLiveData<List<TVShows>> = MutableLiveData()
    private val LoadingFlag : MutableLiveData<Int> = MutableLiveData()
    private val ErrorFlag : MutableLiveData<Int> = MutableLiveData()

    fun loadingStateObserver():MutableLiveData<Int>{
        return LoadingFlag
    }
    fun errorStateObserver():MutableLiveData<Int>{
        return ErrorFlag
    }
//    fun gettvListDataObserver():MutableLiveData<List<TVShows>>{
//        return tvListData
//    }

    fun getTVShowData(callback : (List<TVShows>) -> Unit){

        val apiService = TVShowAPIService.getInstance().create(TVShowAPI::class.java)
        apiService.getTVList().enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                LoadingFlag.postValue(1)
                return callback(response.body()!!.tvshows)
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
                ErrorFlag.postValue(1)
            }
        })
    }
}
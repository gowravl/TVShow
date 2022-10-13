package com.example.tvshow.viewmodels

import android.app.Application
import android.app.ProgressDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.TVShows
import com.example.tvshow.services.TVShowAPI
import com.example.tvshow.services.TVShowAPIService
import com.example.tvshow.tvdetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVShowViewModel(application: Application): AndroidViewModel(application) {

    private var tvListData: MutableLiveData<List<TVShows>> = MutableLiveData()
    private val context = getApplication<Application>().baseContext

    fun gettvListDataObserver():MutableLiveData<List<TVShows>>{
        return tvListData
    }

    fun getTVShowData(callback : (List<TVShows>) -> Unit){
//        val progressDialog = ProgressDialog(context)
//        progressDialog.setTitle("Please Wait")
//        progressDialog.setMessage("API is loading")
//        progressDialog.show()

        val apiService = TVShowAPIService.getInstance().create(TVShowAPI::class.java)
        apiService.getTVList().enqueue(object : Callback<TVShowResponse> {
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                return callback(response.body()!!.tvshows)
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
            }
        })
    }

}
package com.example.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshow.adapters.TVShowAdapter
import com.example.tvshow.models.TVShowResponse
import com.example.tvshow.models.TVShows
import com.example.tvshow.services.TVShowAPI
import com.example.tvshow.services.TVShowAPIService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.recyclerView)
        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.setHasFixedSize(true)
        getTVShowData { tvshows : List<TVShows> ->
            recyclerView1.adapter=TVShowAdapter(tvshows)
        }

    }
    private fun getTVShowData(callback : (List<TVShows>) -> Unit){
        val apiService = TVShowAPIService.getInstance().create(TVShowAPI::class.java)
        apiService.getTVList().enqueue(object : Callback<TVShowResponse>{
            override fun onResponse(call: Call<TVShowResponse>, response: Response<TVShowResponse>) {
                return callback(response.body()!!.tvshows)
            }
            override fun onFailure(call: Call<TVShowResponse>, t: Throwable) {
            }
        })
    }
}
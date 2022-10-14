package com.example.tvshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshow.adapters.TVShowAdapter
import com.example.tvshow.models.TVShows
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tvshow.viewmodels.TVShowViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    //    companion object{
//        val INTENT_PARCELABLE = "OBJECT_INTENT"
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.setHasFixedSize(true)

        val viewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)

        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.apply {
            setTitle("Please Wait")
            setMessage("API is loading")
            show()
        }
        viewModel.loadingStateObserver().observe(this, Observer<Int> {
            progressDialog.dismiss()
        })

        viewModel.errorStateObserver().observe(this, Observer<Int> {
            val intent = Intent(this, NoConnection::class.java)
            startActivity(intent)
        })

        GlobalScope.launch(Dispatchers.Main) {
            viewModel.getTVShowData { tvshows: List<TVShows> ->
                recyclerView1.adapter = TVShowAdapter(tvshows) {
                    val intent = Intent(this, tvdetails::class.java)
                    //                intent.putExtra(INTENT_PARCELABLE,it)
                    startActivity(intent)
                }
            }
//        viewModel.getTVShowData{ tvshows : List<TVShows> ->
//            recyclerView1.adapter=TVShowAdapter(tvshows){
//                val intent = Intent(this, tvdetails::class.java)
//    //                intent.putExtra(INTENT_PARCELABLE,it)
//                startActivity(intent)
//            }
//        }
        }
    }
}
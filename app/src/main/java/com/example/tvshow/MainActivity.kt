package com.example.tvshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshow.adapters.TVShowAdapter
import com.example.tvshow.models.TVShows
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tvshow.viewmodels.TVShowViewModel

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
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("API is loading")
        progressDialog.show()
//    viewModel.gettvListDataObserver().observe(this, Observer<List<TVShows>>{
//            if(it != null){
//            }
//            else{
//                Toast.makeText(this,"Error",Toast.LENGTH_LONG).show()
//            }
//        })
        viewModel.getTVShowData{ tvshows : List<TVShows> ->
            recyclerView1.adapter=TVShowAdapter(tvshows){
                progressDialog.dismiss()
                val intent = Intent(this, tvdetails::class.java)
//                intent.putExtra(INTENT_PARCELABLE,it)
                startActivity(intent)
            }
        }
    }
}
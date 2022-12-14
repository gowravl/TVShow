package com.example.tvshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tvshow.adapters.TVShowAdapter
import com.example.tvshow.models.TVShows
import kotlinx.android.synthetic.main.activity_main.*
import com.example.tvshow.viewmodels.TVShowViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val tvAdapter by lazy { TVShowAdapter(::onItemClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView1.layoutManager = LinearLayoutManager(this)
        recyclerView1.setHasFixedSize(true)
        val viewModel = ViewModelProviders.of(this).get(TVShowViewModel::class.java)
        recyclerView1.adapter = tvAdapter
        val progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.apply {
            setTitle(getString(R.string.pleasewait))
            setMessage(getString(R.string.apiloading))
            show()
        }
        viewModel.apply {
            loadingStateObserver().observe(this@MainActivity, Observer<Boolean> {
                progressDialog.dismiss()
            })
            errorStateObserver().observe(this@MainActivity, Observer<Boolean> {
                val intent = Intent(this@MainActivity, NoConnection::class.java)
                startActivity(intent)
            })
            getData()
            getLiveDataObserver().observe(this@MainActivity,Observer<List<TVShows>>{tvshows->
                tvAdapter.setListData(tvshows)
            })
        }
    }

    private fun onItemClicked(item: TVShows) {
            intent=Intent(this,tvdetails::class.java)
            intent.putExtra("tvshowid",item.id)
            intent.putExtra("tvtitle",item.title)
            intent.putExtra("tvback",item.backdrop)
            startActivity(intent)
    }
}
package com.example.tvshow

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.tvshow.models.TVShowDetails
import com.example.tvshow.viewmodels.TVDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_tvdetails.*

@AndroidEntryPoint
class tvdetails : AppCompatActivity() {
    lateinit var tablayout : TabLayout
    lateinit var viewPager: ViewPager
    lateinit var adapter:FragmentsAdapter
    lateinit var title: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvdetails)
        tablayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        val tvid = intent.getStringExtra("tvshowid")?.let { Integer.parseInt(it) }
        val tvtitle = intent.getStringExtra("tvtitle")
        val tvbackdrop = intent.getStringExtra("tvback")
        val IMAGE_URL = "https://image.tmdb.org/t/p/original/"
        Glide.with(applicationContext).load(IMAGE_URL+tvbackdrop).into(backdrop)
        title = findViewById(R.id.tvDetailsTitle)
        tablayout.setupWithViewPager(viewPager)
        adapter = FragmentsAdapter(supportFragmentManager, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewModel1 = ViewModelProviders.of(this)[TVDetailsViewModel::class.java]
        title.text = tvtitle
        val progressDialog = ProgressDialog(this@tvdetails)
        progressDialog.apply {
            setTitle(getString(R.string.pleasewait))
            setMessage(getString(R.string.apiloading))
            show()
        }
        viewModel1.apply {
            loadingStateObserver().observe(this@tvdetails, Observer<Boolean> {
                progressDialog.dismiss()
            })
            errorStateObserver().observe(this@tvdetails, Observer<Boolean> {
                val intent = Intent(this@tvdetails, NoConnection::class.java)
                startActivity(intent)
            })
            viewModel1.getData(tvid)
            viewModel1.getLiveDataObserver().observe(this@tvdetails, Observer<TVShowDetails> { tvdata->
                Glide.with(applicationContext).load(IMAGE_URL+tvdata.poster).into(tvDetailsPoster)
                AboutFragment(tvdata.overview).let { adapter.addFragment(it,"About") }
                adapter.addFragment(SeasonsFragment(),"Seasons")
            })
            viewPager.adapter = adapter
        }
    }
}


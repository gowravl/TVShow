package com.example.tvshow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.tvshow.R
import com.example.tvshow.FragmentsAdapter
import com.example.tvshow.AboutFragment

class tvdetails : AppCompatActivity() {
    lateinit var tablayout : TabLayout
    lateinit var viewPager: ViewPager
    lateinit var adapter:FragmentsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvdetails)
        tablayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        val description = intent.getStringExtra("description")
        tablayout.setupWithViewPager(viewPager)
        adapter = FragmentsAdapter(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        description?.let { AboutFragment(it) }?.let { adapter.addFragment(it,"STORYLINE") }
//        adapter.addFragment(CastFragment(castList),"CAST")
        viewPager.adapter = adapter

    }

}


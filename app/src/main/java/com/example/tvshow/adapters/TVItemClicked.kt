package com.example.tvshow.adapters

import com.example.tvshow.models.TVShows

interface TVItemClicked{
    fun onItemClicked(item:TVShows){
    }
}
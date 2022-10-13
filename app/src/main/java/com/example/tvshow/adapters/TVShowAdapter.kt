package com.example.tvshow.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshow.R
import com.example.tvshow.models.TVShows
import kotlinx.android.synthetic.main.home_activity_card.view.*

class TVShowAdapter(
//    private val context : Context,
    private val tvshows : List<TVShows>,
    val listener: (TVShows)->Unit
    ): RecyclerView.Adapter<TVShowAdapter.TVViewHolder>(){
        class TVViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
            fun bindTV(tvShow: TVShows,listener: (TVShows)->Unit){
                itemView.title.text = tvShow.title
                Glide.with(itemView).load(IMAGE_BASE+tvShow.backdrop).into(itemView.backdrop)
                itemView.setOnClickListener { listener.invoke(tvShow) }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        return TVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_activity_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bindTV(tvshows.get(position),listener)
    }

    override fun getItemCount(): Int = tvshows.size
}

package com.example.tvshow.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshow.R
import com.example.tvshow.models.TVShows
import com.example.tvshow.tvdetails
import kotlinx.android.synthetic.main.home_activity_card.view.*

class TVShowAdapter(
    private val context : Context,
    private val tvshows : List<TVShows>,
    ): RecyclerView.Adapter<TVShowAdapter.TVViewHolder>(){
        class TVViewHolder(view: View) : RecyclerView.ViewHolder(view){
            private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
            fun bindTV(tvShow: TVShows){
                itemView.title.text = tvShow.title
                Glide.with(itemView).load(IMAGE_BASE+tvShow.backdrop).into(itemView.backdrop)
        }}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        return TVViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.home_activity_card,parent,false)
        )
    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bindTV(tvshows.get(position))
        holder.itemView.setOnClickListener {
                val intent = Intent(context, tvdetails::class.java)
                context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = tvshows.size
}
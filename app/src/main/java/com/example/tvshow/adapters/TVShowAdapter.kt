package com.example.tvshow.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshow.R
import com.example.tvshow.models.TVShows
import kotlinx.android.synthetic.main.home_activity_card.view.*

class TVShowAdapter(
    private val onClick: TVItemClicked
    ): RecyclerView.Adapter<TVShowAdapter.TVViewHolder>(){
    inner class TVViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindTV(tvShow: TVShows){
            itemView.title.text = tvShow.title
            Glide.with(itemView).load(IMAGE_BASE+tvShow.backdrop).into(itemView.backdrop)
        }
    }
    var items : MutableList<TVShows> = ArrayList()

    fun setListData(tvshow: List<TVShows>) {
        items.addAll(tvshow)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_activity_card,parent,false)
        val viewHolder = TVViewHolder(view)
        view.setOnClickListener {
            onClick.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder

    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bindTV(items.get(position))

    }

    override fun getItemCount(): Int = items.size
}
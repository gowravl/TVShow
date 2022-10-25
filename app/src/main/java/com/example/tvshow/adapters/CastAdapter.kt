package com.example.tvshow.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tvshow.R
import com.example.tvshow.models.Cast
import kotlinx.android.synthetic.main.cast_item.view.*

class CastAdapter(): RecyclerView.Adapter<CastAdapter.TVViewHolder>(){
    inner class TVViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val IMAGE_BASE = "https://image.tmdb.org/t/p/original/"
        fun bindTV(cast: Cast){
            itemView.cast_name.text = cast.name
            itemView.cast_character.text = cast.character
            Glide.with(itemView).load(IMAGE_BASE+cast.profilePath).into(itemView.cast_photo)
        }
    }
    var items : MutableList<Cast> = ArrayList()

    fun setListData(cast: List<Cast>){
        items.addAll(cast)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TVViewHolder {
        Log.d("Tag","Binded")

        val view = LayoutInflater.from(parent.context).inflate(R.layout.cast_item,parent,false)
        val viewHolder = TVViewHolder(view)
        return viewHolder

    }

    override fun onBindViewHolder(holder: TVViewHolder, position: Int) {
        holder.bindTV(items.get(position))

    }

    override fun getItemCount(): Int = items.size
}
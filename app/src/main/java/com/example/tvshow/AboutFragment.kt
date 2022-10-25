package com.example.tvshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tvshow.adapters.CastAdapter
import com.example.tvshow.models.Cast
import com.example.tvshow.models.Video
import com.example.tvshow.models.VideosResponse
import com.example.tvshow.viewmodels.TVDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_about.*
@AndroidEntryPoint
class AboutFragment(val description : String) : Fragment() {
    val castAdapter by lazy { CastAdapter() }
    lateinit var fragmentDescription: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_about, container, false)
        fragmentDescription = view.findViewById(R.id.tvDetailsOverview)
        fragmentDescription.text = description
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel1 = ViewModelProvider(requireActivity()).get(TVDetailsViewModel::class.java)
        cast_rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        cast_rv.adapter = castAdapter
        viewModel1.getLiveDataObserverCast()
            .observe(viewLifecycleOwner, Observer<List<Cast>> { castdata ->
                castAdapter.setListData(castdata)
            })
        viewModel1.getLiveDataObserverVideo()
            .observe(viewLifecycleOwner, Observer<VideosResponse> { viddata ->
                setShowVideos(viddata)
            })
    }

    private fun setShowVideos(videosResponse: VideosResponse?) {
        tvTrailers.removeAllViews()
        if (videosResponse != null) {
            for ((key, name) in videosResponse.videos) {
                val parent = layoutInflater.inflate(R.layout.trailer_item, tvTrailers, false)
                val thumbnail = parent.findViewById<ImageView>(R.id.thumbnail_trailer)
                val tvTrailerTitle = parent.findViewById<TextView>(R.id.trailerTitle)
                tvTrailerTitle.text = name
                Glide.with(this)
                    .load(
                        String.format("https://img.youtube.com/vi/%s/0.jpg", key)
                    )
                    .apply(RequestOptions.placeholderOf(R.color.blue).centerCrop())
                    .into(thumbnail)
                thumbnail.requestLayout()
                thumbnail.setOnClickListener {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(String.format("https://m.youtube.com/watch?v=%s", key))
                        )
                    )
                }
                tvTrailers.addView(parent)
            }
        }
    }
}
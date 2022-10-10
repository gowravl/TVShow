package com.example.tvshow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AboutFragment(val description : String) : Fragment() {
        lateinit var fragmentDescription: TextView
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            val view:View = inflater.inflate(R.layout.fragment_about, container, false)
            fragmentDescription = view.findViewById(R.id.tvDetailsOverview)
            fragmentDescription.text = description
            return view
        }

    }
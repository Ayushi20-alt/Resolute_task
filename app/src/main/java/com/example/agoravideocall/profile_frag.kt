package com.example.agoravideocall

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class profile_frag : Fragment() {

    private lateinit var button1 : Button
    private lateinit var button2 : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view = inflater.inflate(R.layout.fragment_profile_frag, container, false)
        button1 = view.findViewById(R.id.buttonvideo)
        button1.setOnClickListener {
            findNavController().navigate(R.id.action_profile_frag_to_mainActivity)
        }
        button2 = view.findViewById(R.id.buttongraph)
        button2.setOnClickListener {
            findNavController().navigate(R.id.action_profile_frag_to_mapsActivity)
        }
        return view
    }

}
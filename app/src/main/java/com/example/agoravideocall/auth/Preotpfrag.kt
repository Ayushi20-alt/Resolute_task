package com.example.agoravideocall.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.agoravideocall.R
import com.example.agoravideocall.databinding.FragmentPreotpfragBinding
import com.google.firebase.auth.FirebaseAuth

class preotpfrag : Fragment() {
    private lateinit var auth: FirebaseAuth
//    private  var currentUser: FirebaseUser? = null
//    private var phoneNumber: String =" "
    private var _binding : FragmentPreotpfragBinding? = null
    // point binding ko hi krega bus hum null safe krane ke liye use krege
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        auth = Firebase.auth
//        currentUser = auth.currentUser
        auth = FirebaseAuth.getInstance()
        _binding = FragmentPreotpfragBinding.inflate(inflater, container, false)

       binding.Button.setOnClickListener{
           if(binding.textInputEditText3.text!!.isEmpty())
           {
               Toast.makeText(activity,"Please Enter your Number!!", Toast.LENGTH_LONG).show()
           }
           else
           {
               findNavController().navigate(R.id.action_preotpfrag_to_otpfrag, Bundle().apply {
                   putString("PhoneNo", binding.textInputEditText3.text.toString())
           })
       }
       }

        return binding.root
    }


}
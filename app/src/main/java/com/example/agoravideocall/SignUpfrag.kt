package com.example.agoravideocall

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignUpfrag : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize Firebase Auth
        auth = Firebase.auth


        val view = inflater.inflate(R.layout.fragment_sign_upfrag, container, false)
        val Button = view.findViewById<Button>(R.id.materialButton1)
        Button.setOnClickListener{
//            view.findNavController().navigate(R.id.action_signUpfrag_to_mainActivity)
            performSignUp()
        }
        // lets get email and password from user


        return view

    }
    @SuppressLint("SuspiciousIndentation")
    private fun performSignUp() {
        val email = view?.findViewById<TextInputEditText>(R.id.textInputEditText2)
        val pass = view?.findViewById<TextInputEditText>(R.id.textInputEditText1)


        if (email != null) {
            if (pass != null) {
                if (email.text?.isEmpty() == true || pass.text?.isEmpty()  == true)
                {
                       Toast.makeText(activity, "Please fill all the fields", Toast.LENGTH_LONG).show()
                }
            }
        }


        val inputmail = email?.text.toString()
        val inputpass = pass?.text.toString()

        auth.createUserWithEmailAndPassword(inputmail, inputpass).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, lets move to another activity then
                  findNavController().navigate(R.id.action_signUpfrag_to_preotpfrag)
                    Toast.makeText(activity,
                        "Success.",
                        Toast.LENGTH_SHORT).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(activity,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }

            .addOnFailureListener {
                Toast.makeText(activity,"Error Occured. $(it.LocalizedMessage)",Toast.LENGTH_LONG).show()
            }
    }

}
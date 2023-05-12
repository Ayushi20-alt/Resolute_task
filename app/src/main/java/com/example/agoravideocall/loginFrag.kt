package com.example.agoravideocall

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

class loginFrag : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Initialize Firebase Auth
        auth = Firebase.auth

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val button = view.findViewById<Button>(R.id.materialButton)

        button.setOnClickListener{
            performLogin()
        }

        val textSignUp = view.findViewById<TextView>(R.id.txtSignUp)
        textSignUp.setOnClickListener {
          findNavController().navigate(R.id.action_loginFrag_to_signUpfrag)
        }

        return view
    }

    private fun performLogin() {
        val email = view?.findViewById<TextInputEditText>(R.id.textInputEditText1)
        val pass = view?.findViewById<TextInputEditText>(R.id.inputtextpassword)

        if (email != null) {
            if (pass != null) {
                if (email.text?.isEmpty() == true || pass.text?.isEmpty() == true) {
                    Toast.makeText(activity, "Please fill all the fields", Toast.LENGTH_LONG).show()
                }
            }
        }

        val inputEmail = email?.text.toString()
        val inputPass = pass?.text.toString()

        auth.signInWithEmailAndPassword(inputEmail, inputPass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to mainActivity

                    findNavController().navigate(R.id.action_loginFrag_to_mainActivity)
                    Toast.makeText(activity,
                        "Success.",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                       activity,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                }
            }
            .addOnFailureListener {
                Toast.makeText(activity,"Authentication failed. $(it.LocalizedMessage)",Toast.LENGTH_LONG).show()
            }
    }
}

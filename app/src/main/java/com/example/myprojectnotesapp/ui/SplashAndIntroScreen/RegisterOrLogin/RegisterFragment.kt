package com.example.myprojectnotesapp.ui.SplashAndIntroScreen.RegisterOrLogin

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding
    private lateinit var mAuth: FirebaseAuth
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_login, container, false)


}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAuth = FirebaseAuth.getInstance()
        val firebaseDatabase = FirebaseFirestore.getInstance()

        binding.register.setOnClickListener {
            if (binding.editTextUsername.text.toString().isEmpty()) {
                binding.editTextUsername.error = ("Your username is empty")
            } else if (binding.editTextPhone.text.toString().isEmpty()) {
                binding.editTextPhone.error = ("Phone is not found")
            } else if (binding.editTextPassword.text.toString().isEmpty()) {
                binding.editTextPassword.error = ("password is empty")
            } else {
                mAuth.createUserWithEmailAndPassword(
                    binding.editTextPhone.text.toString(),
                    binding.editTextPassword.text.toString()
                )
            }
            }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

}
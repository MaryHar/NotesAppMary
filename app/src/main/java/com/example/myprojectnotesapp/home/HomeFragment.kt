package com.example.myprojectnotesapp.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.myprojectnotesapp.R
import com.google.firebase.database.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var database: FirebaseDatabase
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        database = FirebaseDatabase.getInstance()
    }
}
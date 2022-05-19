package com.example.myprojectnotesapp


import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        val navigation = findViewById<FragmentContainerView>(R.id.fragmentContainerView)

        val handle = Handler()
        handle.postDelayed({
            navigation.findNavController().navigate(R.id.introFragment)
        },3000)

    }

}
package com.example.myprojectnotesapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.myprojectnotesapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()

        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val controller = findNavController(R.id.nav_host_fragment_notes)

        val appBarConfig = AppBarConfiguration(

            setOf(
                R.id.notes,
                R.id.profile,
                R.id.create_notes
            )
        )
        //setupActionBarWithNavController(controller, appBarConfig)
        navView.setupWithNavController(controller)

    }
}
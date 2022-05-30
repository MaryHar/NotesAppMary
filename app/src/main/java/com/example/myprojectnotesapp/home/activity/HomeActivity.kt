package com.example.myprojectnotesapp.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.adapter.SectionsPagerAdapter
import com.example.myprojectnotesapp.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
        val navView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val controller = findNavController(R.id.nav_host_fragment_notes)
        navView.setupWithNavController(controller)
    }
    private fun initView() {

        val sectionsPagerAdapter = SectionsPagerAdapter(this,
            supportFragmentManager
        )
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(view_pager)

    }

    private fun initListener() {
        binding.toolbar.ibSearch.setOnClickListener(this)
        binding.floatingActionButton.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.floatingActionButton -> {
                startActivity(Intent(this, EditActivity::class.java))
            }
        }
    }
}
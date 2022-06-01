package com.example.myprojectnotesapp.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.adapter.SectionsPagerAdapter
import com.example.myprojectnotesapp.databinding.ActivityHomeBinding
import com.example.myprojectnotesapp.databinding.BottomsheetNoteBinding
import com.example.myprojectnotesapp.databinding.BottomsheetSettingsBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.bottomsheet_settings.*

class HomeActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialog: BottomSheetDialog


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()
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
        binding.toolbar.ibMenu.setOnClickListener(this)
    }
    private fun showBottomSheet() {
        val views: View =
            LayoutInflater.from(this).inflate(R.layout.bottomsheet_settings, null)

        val bindingBottom = BottomsheetSettingsBinding.bind(views)

        dialog = BottomSheetDialog(this)
        dialog.setCancelable(true)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(views)
        dialog.show()

        bindingBottom.clDelete.setOnClickListener(this)
        bindingBottom.clShare.setOnClickListener(this)
        bindingBottom.clSignOut.setOnClickListener(this)

    }
    private fun signOutListener() {
        cl_signOut.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this, MainActivity::class.java))
            this?.finish()
            //  findNavController().navigate(R.id.action_profile_to_registerFragment2)

        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.ib_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.ib_menu -> {
                showBottomSheet()
            }

            R.id.cl_signOut -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
                this?.finish()
            }
            R.id.floatingActionButton -> {
                startActivity(Intent(this, EditActivity::class.java))
            }

        }
    }


}
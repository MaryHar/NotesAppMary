package com.example.myprojectnotesapp.home.activity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.adapter.SectionsPagerAdapter
import com.example.myprojectnotesapp.databinding.ActivityHomeBinding
import com.example.myprojectnotesapp.databinding.BottomsheetSettingsBinding
import com.example.myprojectnotesapp.voice.view.MainVoiceActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() , View.OnClickListener {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var dialog: BottomSheetDialog
    private lateinit var changeDialog: View
    private lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initListener()

    }

    private fun initView() {

        val sectionsPagerAdapter = SectionsPagerAdapter(
            this,
            supportFragmentManager
        )
        binding.viewPager.adapter = sectionsPagerAdapter
        binding.tabs.setupWithViewPager(view_pager)

    }

    private fun initListener() {
        binding.toolbar.ibSearch.setOnClickListener(this)
        binding.floatingActionButton.setOnClickListener(this)
        binding.toolbar.ibMenu.setOnClickListener(this)
        binding.toolbar.ibVoice.setOnClickListener(this)
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

        bindingBottom.clChangePassword.setOnClickListener(this)
        bindingBottom.clSignOut.setOnClickListener(this)

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
            R.id.ib_voice -> {
                val intent = Intent(this, MainVoiceActivity::class.java)
                startActivity(intent)
            }
            R.id.cl_signOut -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, MainActivity::class.java))
                this?.finish()
            }
            R.id.floatingActionButton -> {
                startActivity(Intent(this, EditActivity::class.java))
            }

            R.id.cl_changePassword -> {
                changeDialog =
                    LayoutInflater
                        .from(this)
                        .inflate(R.layout.change_password_dialog, null)

                val mBuilder = AlertDialog.Builder(this)
                    .setView(changeDialog)
                    .setTitle("Change Password")
                val mAlertDialog = mBuilder.show()

                changeDialog.findViewById<Button>(R.id.buttonCancel).setOnClickListener {
                    mAlertDialog.dismiss()
                }
                changeDialog.findViewById<Button>(R.id.buttonChangePassword).setOnClickListener {
                    editTextPassword = changeDialog.findViewById(R.id.editTextPassword)
                    val passwordChange = editTextPassword.text.toString()

                        if (passwordChange.isEmpty()) {
                            editTextPassword.error = "Password is empty!"
                        }
                        if (!(passwordChange.matches(".*[A-Z].*".toRegex())) &&
                            !(passwordChange.matches(".*[a-z].*".toRegex()))
                        ) {
                            editTextPassword.error = "Must contain letters"
                            return@setOnClickListener
                        }
                        if (!(passwordChange.matches(".*[0-9].*".toRegex()))) {
                            editTextPassword.error = "Must contain digits"
                            return@setOnClickListener
                        }
                        if (!(passwordChange.matches(".*[$@#!?_].*".toRegex()))) {
                            editTextPassword.error = "Must contain special symbols '$@#!?_'"
                            return@setOnClickListener
                        }
                        FirebaseAuth.getInstance().currentUser?.updatePassword(passwordChange)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    startActivity(Intent(this, HomeActivity::class.java))

                                } else {
                                    mAlertDialog.dismiss()

                                }
                            }
                }

                    }

                }
            }

        }


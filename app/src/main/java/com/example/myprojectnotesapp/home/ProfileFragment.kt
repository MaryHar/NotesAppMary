package com.example.myprojectnotesapp.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var imageViewPicture: ImageView
    private lateinit var editTextName: EditText
    private lateinit var textViewMail: TextView
    private lateinit var signOut: Button
    private lateinit var textViewChangePassword: TextView
    private lateinit var buttonSave: Button
    private lateinit var editTextUrl: EditText
    private lateinit var editTextBio: EditText

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        signOutListener()
        changePasswordListener()
        saveButtonListeners()

//      Fun for profile textView mail
        val userMail = FirebaseAuth.getInstance().currentUser?.email
        textViewMail.text = userMail

        db.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo: UserInfo = snapshot.getValue(UserInfo::class.java) ?: return

                if (userInfo.name.isNotEmpty()) {
                    editTextName.hint = userInfo.name
                }
                if (userInfo.bio.isNotEmpty()) {
                    editTextBio.hint = userInfo.bio
                }
                if (userInfo.url.isNotEmpty()) {
                    editTextUrl.hint = userInfo.url
                }

                Glide.with(requireActivity()).load(userInfo.url)
                    .placeholder(R.drawable.profile_picture).into(imageViewPicture)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(activity, "DBerror", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun init() {
        imageViewPicture = requireView().findViewById(R.id.imageViewPicture)
        textViewMail = requireView().findViewById(R.id.textViewMail)
        signOut = requireView().findViewById(R.id.signOut)
        textViewChangePassword = requireView().findViewById(R.id.textViewChangePassword)
        buttonSave = requireView().findViewById(R.id.buttonSave)
        editTextName = requireView().findViewById(R.id.editTextName)
        editTextUrl = requireView().findViewById(R.id.editTextUrl)
        editTextBio = requireView().findViewById(R.id.editTextBio)
    }

    private fun changePasswordListener() {
        textViewChangePassword.setOnClickListener() {
            findNavController().navigate(R.id.action_profileFragment_to_changePasswordFragment2)
        }
    }

    private fun saveButtonListeners() {
        buttonSave.setOnClickListener {

            val name = editTextName.text.toString()
            val url = editTextUrl.text.toString()
            val bio = editTextBio.text.toString()
            val userInfo = UserInfo(name, url, bio)

            if (name.isEmpty()) {
                editTextName.error = "Enter Name"
            }

            editTextUrl.hint = url
            editTextBio.hint = bio


            db.child(auth.currentUser?.uid!!).setValue(userInfo)
            Toast.makeText(activity, "Changes Saved Successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signOutListener() {
        signOut.setOnClickListener() {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(activity, MainActivity::class.java))
            activity?.finish()
          //  findNavController().navigate(R.id.action_profile_to_registerFragment2)

        }
    }
}





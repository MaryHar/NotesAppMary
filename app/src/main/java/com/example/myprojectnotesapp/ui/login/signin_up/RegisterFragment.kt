package com.example.myprojectnotesapp.ui.login.signin_up

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.home.HomeActivity
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment: Fragment(R.layout.fragment_register) {
    private lateinit var editTextMail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editPassword2: EditText
    private lateinit var signInButton: Button
    private lateinit var buttonLoginFragment : TextView
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    private val emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+"



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        registerListeners()

        buttonLoginFragment.setOnClickListener(){
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)

        }
    }

    private fun init(){
        editTextMail = requireView().findViewById(R.id.editTextMail)
        editPassword = requireView().findViewById(R.id.editPassword)
        editPassword2 = requireView().findViewById(R.id.editPassword2)
        signInButton = requireView().findViewById(R.id.signInButton)
        buttonLoginFragment = requireView().findViewById(R.id.buttonLoginFragment)

    }
    private fun registerListeners(){
        signInButton.setOnClickListener(){
            val mail = editTextMail.text.toString()
            val pass = editPassword.text.toString()
            val pass2 = editPassword2.text.toString()

            if(mail.isEmpty()){
                editTextMail.error = "Mail is empty!"
                return@setOnClickListener
            }
            if(pass.isEmpty() || pass2.isEmpty()){
                editPassword.error = "Password is empty!"
                return@setOnClickListener
            }
            if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                editTextMail.error = "Enter valid e-mail address. Try again."
                return@setOnClickListener
            }
            if(!(mail.matches(emailPattern.toRegex())) && !(mail.matches(emailPattern2.toRegex()))){
                editTextMail.error = "Incorrect Email"
                return@setOnClickListener
            }
            if (!(pass.matches(".*[A-Z].*".toRegex())) && !(pass.matches(".*[a-z].*".toRegex())) ){
                editPassword.error = "Must contain letters"
                return@setOnClickListener
            }
            if (!(pass.matches(".*[0-9].*".toRegex()))){
                editPassword.error = "Must contain digits"
                return@setOnClickListener
            }
            if (!(pass.matches(".*[$@#!?_].*".toRegex()))){
                editPassword.error = "Must contain special symbols '$@#!?_'"
                return@setOnClickListener
            }
            if (pass != pass2){
                editPassword2.error = "Passwords don't match"
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener{ task-> if(task.isSuccessful) {
                    Toast. makeText(getActivity(),"You signed up succesfully!",Toast. LENGTH_SHORT)
                        . show();
                    // findNavController().navigate(R.id.action_registerFragment_to_homeFragment2)
                    startActivity(Intent(activity, HomeActivity::class.java))
                    getActivity()?.finish()
                }
                else Toast.makeText(getActivity(), "Error!", Toast.LENGTH_SHORT).show()}


        }
    }
}
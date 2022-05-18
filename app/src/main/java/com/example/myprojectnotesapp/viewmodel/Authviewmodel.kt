package com.roman.authenticationfirebasemvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.roman.authenticationfirebasemvvm.repository.AuthenticationRepository

class Authviewmodel : ViewModel(){
    private var repository = AuthenticationRepository()
    private var userData = repository.getfirebaseusermutablelivedata()
    private var loggedStatus = repository.getUserloogedmutablelivedata()

    fun getuserData() : MutableLiveData<FirebaseUser> {
        return userData
    }

    fun getloggedStatus() : MutableLiveData<Boolean>{
        return loggedStatus
    }

    fun register(email : String , password : String){
        repository.register(email, password)
    }

    fun signIn (email : String, password: String){
        repository.login(email, password)
    }
    fun signout(){
        repository.signOut()
    }
}
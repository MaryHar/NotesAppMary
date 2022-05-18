package com.roman.authenticationfirebasemvvm.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationRepository {
    private lateinit var firebaseusermutablelivedata : MutableLiveData<FirebaseUser>
    private lateinit var auth : FirebaseAuth
    private lateinit var userloggedmutablelivedata : MutableLiveData<Boolean>

    constructor(){
        firebaseusermutablelivedata = MutableLiveData<FirebaseUser>()
        userloggedmutablelivedata = MutableLiveData<Boolean>()
        auth = FirebaseAuth.getInstance()

        if(auth.currentUser != null){
            firebaseusermutablelivedata.postValue(auth.currentUser)
        }

    }

    fun getUserloogedmutablelivedata () : MutableLiveData<Boolean>{
        return userloggedmutablelivedata
    }

    fun getfirebaseusermutablelivedata() : MutableLiveData<FirebaseUser>{
        return firebaseusermutablelivedata
    }

    fun register(email : String , password : String){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                firebaseusermutablelivedata.postValue(auth.currentUser)
            }
        }
    }

    fun login (email : String , password : String){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful){
                firebaseusermutablelivedata.postValue(auth.currentUser)
            }
        }
    }

    fun signOut(){
        auth.signOut()
        userloggedmutablelivedata.postValue(true)
    }
}
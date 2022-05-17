package com.example.myprojectnotesapp.ui.SplashAndIntroScreen.SplashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myprojectnotesapp.MainActivity
import com.example.myprojectnotesapp.R
import kotlinx.coroutines.*

class FirstSplashScreenActivity : AppCompatActivity() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_splash_screen)

        activityScope.launch {
            delay(3000)

            val intent = Intent(this@FirstSplashScreenActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}

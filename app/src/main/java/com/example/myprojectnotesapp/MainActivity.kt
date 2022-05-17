package com.example.myprojectnotesapp

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.myprojectnotesapp.ui.SplashAndIntroScreen.Intro.HomeActivity
import com.example.myprojectnotesapp.ui.SplashAndIntroScreen.Intro.IntroSlide
import com.example.myprojectnotesapp.ui.SplashAndIntroScreen.Intro.IntroSliderAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Notes",
                "Manage your notes easily.",
                R.drawable.splash_screen
            ),
            IntroSlide(
                "Diary",
                "Manage your notes easily.",
                R.drawable.splash_screen2
            ),
            IntroSlide(
                "Register and Login",
                "Hurry to register.Register and Login",
                R.drawable.login_or_register
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val introSliderViewPager = findViewById<ViewPager2>(R.id.introSliderViewPager)
        introSliderViewPager.adapter = introSliderAdapter
        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)

            }
        })
        val buttonNext = findViewById<Button>(R.id.buttonNext)

        buttonNext.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem += 1
            } else {

                Intent(applicationContext, HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }

        val textSkipIntro = findViewById<TextView>(R.id.textSkipIntro)

        textSkipIntro.setOnClickListener {
            Intent(applicationContext, HomeActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }


    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )

                )
                this?.layoutParams = layoutParams
            }

            indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index: Int) {

        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }

}
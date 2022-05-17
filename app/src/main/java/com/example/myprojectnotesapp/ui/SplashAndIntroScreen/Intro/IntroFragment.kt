package com.example.myprojectnotesapp.ui.SplashAndIntroScreen.Intro

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.databinding.FragmentIntroBinding


class IntroFragment : Fragment() {
    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.e("TAG", "onViewCreated: " )
        val viewpager=  binding.introSliderViewPager.adapter
        binding.introSliderViewPager.adapter = IntroSliderAdapter(getIntroData())


        val springDotsIndicator = binding.springDotsIndicator
        springDotsIndicator.attachTo(binding.introSliderViewPager)
        binding.introSliderViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
            override fun onPageSelected(position: Int) {

            }

        })

    }
    private fun getIntroData(): MutableList<IntroSlide> {
        val introList :MutableList<IntroSlide> = ArrayList()
        introList.add(IntroSlide(R.drawable.splash_screen,getString(R.string.next),getString(R.string.back)))
        introList.add(IntroSlide(R.drawable.splash_screen2,getString(R.string.next),getString(R.string.back)))
        introList.add(IntroSlide(R.drawable.login_or_register,getString(R.string.next),getString(R.string.back),))
        return introList
    }

}
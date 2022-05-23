package com.example.myprojectnotesapp.ui.login.Intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.myprojectnotesapp.R

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) : PagerAdapter() {

    override fun instantiateItem(collection: ViewGroup, position: Int): Any {
        val intro = introSlides[position]
        val inflater = LayoutInflater.from(collection.context)
        val layout =
            inflater.inflate(R.layout.slider_item_container, collection, false) as ViewGroup
        collection.addView(layout)
        val screenImage = layout.findViewById<ImageView>(R.id.imageSlideIcon)
        val screenText = layout.findViewById<TextView>(R.id.textTitle)
        val descText = layout.findViewById<TextView>(R.id.textDescription)


        Glide.with(collection.context).load(intro.icon).into(screenImage)
        screenText.text = intro.title
        descText.text = intro.description
        return layout
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View?)
    }

    override fun getCount(): Int {
        return introSlides.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

}
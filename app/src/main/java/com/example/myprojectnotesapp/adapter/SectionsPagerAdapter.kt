package com.example.myprojectnotesapp.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myprojectnotesapp.R
import com.example.myprojectnotesapp.fragment.AllNotesFragment
import com.example.myprojectnotesapp.fragment.PersonalNotesFragment
import com.example.myprojectnotesapp.fragment.StudyNotesFragment
import com.example.myprojectnotesapp.fragment.WorkNotesFragment
import com.example.myprojectnotesapp.home.ProfileFragment


class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    @StringRes
    private val tabTitles =
        intArrayOf(R.string.tab_all, R.string.tab_personal, R.string.tab_work, R.string.tab_study, R.string.tab_settings)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment =
                AllNotesFragment()
            1 -> fragment =
                PersonalNotesFragment()
            2 -> fragment =
                WorkNotesFragment()
            3 -> fragment =
                StudyNotesFragment()
            4 -> fragment =
                ProfileFragment()

        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(tabTitles[position])
    }

    override fun getCount(): Int {
        return 5
    }
}
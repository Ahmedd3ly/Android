@file:Suppress("DEPRECATION")

package com.example.kemetnews

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.kemetnews.fragments.*

class FragmentAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentPagerAdapter(fm, tabCount) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> Home()
            1 -> Sports()
            2 -> Health()
            3 -> Food()
            4 -> Politics()
            5 -> Environment()
            6 -> Technology()
            7 -> Science()
            8 -> Entertainment()
            9 -> Business()
            else -> Home()
        }
    }
    override fun getCount(): Int {
        return tabCount
    }
}
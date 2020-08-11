package com.idn.fathurwa.Adapter

import androidx.constraintlayout.widget.Placeholder
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.idn.fathurwa.ui.activity.MainActivity
import com.idn.fathurwa.ui.fragment.ChatFragment
import com.idn.fathurwa.ui.fragment.FragmentMain
import com.idn.fathurwa.ui.fragment.StatusListFragment
import com.idn.fathurwa.ui.fragment.StatusUpdateFragment

class SectionPagerAdapter(fm:FragmentManager): FragmentPagerAdapter(fm){

    private val chatFragment = ChatFragment()
    private val statusUpdateFragment = StatusUpdateFragment()
    private val statusListFragment = StatusListFragment()

    override fun getItem(position: Int): Fragment {
       return when (position) {
           0 -> statusUpdateFragment
           1 -> chatFragment
           2 -> statusListFragment
           else -> chatFragment
       }
    }

    override fun getCount(): Int {
        return 3
    }

}

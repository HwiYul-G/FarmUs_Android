package com.farm.farmus_application.presentation.ui.farm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.farm.farmus_application.ui.farm.MyFarmListFragment
import com.farm.farmus_application.ui.farm.ReserveFarmListFragment

private const val NUM = 2
class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = NUM

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ReserveFarmListFragment()
            1 -> MyFarmListFragment()
            else -> ReserveFarmListFragment()
        }
    }
}
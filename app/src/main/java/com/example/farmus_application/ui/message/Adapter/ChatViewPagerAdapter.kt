package com.example.farmus_application.ui.message.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.farmus_application.ui.message.ChatTab1Fragment
import com.example.farmus_application.ui.message.ChatTab2Fragment

private const val NUM =2

class ChatViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle){

    override fun getItemCount(): Int {
        return NUM
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return ChatTab1Fragment()
            1 -> return ChatTab2Fragment()
        }
        return ChatTab1Fragment()
    }
}
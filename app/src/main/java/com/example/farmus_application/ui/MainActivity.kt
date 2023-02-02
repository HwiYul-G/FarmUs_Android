package com.example.farmus_application.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivityMainBinding
import com.example.farmus_application.ui.account.MyPageFragment
import com.example.farmus_application.ui.farm.FarmFragment
import com.example.farmus_application.ui.favorite.FavoriteFragment
import com.example.farmus_application.ui.home.HomeFragment
import com.example.farmus_application.ui.message.ChatFragment

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        changeFrame(mainBinding, HomeFragment.newInstance("","")) //Initialize Frame

        mainBinding.selectFragmentBottomNavi.run {
            setOnItemSelectedListener {
                val switchFragment = when(it.itemId){
                    R.id.menu_favorites -> FavoriteFragment.newInstance("", "")
                    R.id.menu_farm -> FarmFragment.newInstance("", "")
                    R.id.menu_chat -> ChatFragment.newInstance("", "")
                    R.id.menu_my_page -> MyPageFragment.newInstance("", "")
                    else -> HomeFragment.newInstance("","")
                }
                changeFrame(mainBinding, switchFragment)
                true
            }
        }


    }

    fun hideBottomNavigation(bool: Boolean) {
        if(bool) {
            mainBinding.selectFragmentBottomNavi.visibility = View.GONE
        } else {
            mainBinding.selectFragmentBottomNavi.visibility = View.VISIBLE
        }
    }

    private fun changeFrame(binding: ActivityMainBinding, fragment: Fragment){
        supportFragmentManager.commit{
            replace(binding.mainFragmentFrame.id, fragment)
        }
    }

    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.commit{
            replace(mainBinding.mainFragmentFrame.id, fragment)
        }
    }

    //SearchFragment에서 HomeSearchActivity로 전환
    fun changeFragmentToActivity(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }

}
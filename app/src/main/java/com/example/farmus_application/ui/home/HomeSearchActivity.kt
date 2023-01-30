package com.example.farmus_application.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.text.Editable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivityHomeSearchBinding
import com.example.farmus_application.databinding.ActivityMainBinding
import com.example.farmus_application.databinding.FragmentHomeFilterBinding
import com.example.farmus_application.databinding.FragmentSearchBinding
import com.example.farmus_application.ui.MainActivity
import com.google.android.material.chip.Chip


@Suppress("NAME_SHADOWING")
class HomeSearchActivity : AppCompatActivity() {

    private lateinit var homeSearchBinding: ActivityHomeSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeSearchBinding=ActivityHomeSearchBinding.inflate(layoutInflater)
        setContentView(homeSearchBinding.root)

        //activity로 전환되면 바로 검색창 focus 주기
        homeSearchBinding.searchBar.requestFocus()

        // 검색하면 SearchFragment로 전환
        homeSearchBinding.searchBar.setOnClickListener{
            changeFrame(SearchFragment.newInstance("",""))
        }

        //chip 아이템 임의로 설정 (원래는 검색된 단어로 설정)
        val chipItems = mutableListOf<String>()

        chipItems.add("경기도")
        chipItems.add("강원도")
        chipItems.add("서울")
        chipItems.add("충청북도")
        chipItems.add("충청남도")
        chipItems.add("전라북도")
        chipItems.add("전라남도")
        chipItems.add("제주도")

        //chip 동적 추가
        if(chipItems.size > 0) {

            for(i in chipItems) {
                addChip(i)
            }
        }

    }

    //chip 추가
    @SuppressLint("UseCompatLoadingForDrawables")
    private fun addChip(text: String){
        val chip = Chip(this)
        val radius : Float = 7.0f

        chip.text = text
        chip.closeIcon = getDrawable(R.drawable.cancel_vector_image)
        chip.chipStrokeColor = getColorStateList(R.color.gray_1)
        chip.chipCornerRadius = radius
        chip.chipStrokeWidth = 0.5f
        chip.chipBackgroundColor = getColorStateList(R.color.white)
        chip.isCloseIconVisible = true

        chip.setOnCloseIconClickListener {
            homeSearchBinding.recentSearchChipgroup.removeView(chip) //삭제 버튼 누르면 chip 삭제
        }

        //chip 버튼 클릭 이벤트
        chip.setOnClickListener {
            val bundle = Bundle()
            val chipText = chip.text.toString()
            val fragment = SearchFragment.newInstance("","")

            bundle.putString("text", chipText)
            fragment.arguments = bundle
            changeFrame(SearchFragment.newInstance("",""))
        }

        homeSearchBinding.recentSearchChipgroup.addView(chip)
    }

    //뒤로 가기 하면 MainActivity로 전환 설정
   override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    //fragment로 전환하기
    fun changeFrame(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(homeSearchBinding.homeSearchFrame.id,fragment)
            .commit()
    }

    //Fragment에서 Activity로 전환하기
    fun changeFragmentToActivity(activity: Activity){
        val intent = Intent(this, activity::class.java)
        startActivity(intent)
    }
}
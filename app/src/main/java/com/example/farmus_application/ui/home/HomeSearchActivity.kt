package com.example.farmus_application.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.farmus_application.R
import com.example.farmus_application.databinding.ActivityHomeSearchBinding
import com.example.farmus_application.ui.home.Adapter.LocalFarmRVAdapter
import com.example.farmus_application.ui.home.Adapter.ResultRVAdapter

class HomeSearchActivity : AppCompatActivity() {

    private lateinit var homeSearchBinding: ActivityHomeSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeSearchBinding=ActivityHomeSearchBinding.inflate(layoutInflater)
        setContentView(homeSearchBinding.root)

        //dropdown
        val filter_dropdown_item = resources.getStringArray(R.array.filter_list)
        homeSearchBinding.filterDropdown.adapter = ArrayAdapter(this, R.layout.activity_home_search ,filter_dropdown_item)

        homeSearchBinding.filterDropdown.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        //검색 결과 아이템
        val result_farm_items = mutableListOf<RVFarmDataModel>()

        //검색 결과 농장 리사이클러뷰
        homeSearchBinding.rvHomeSearchFarm.adapter = ResultRVAdapter(result_farm_items)
        homeSearchBinding.rvHomeSearchFarm.layoutManager = GridLayoutManager(this, 2)

        //툴바의 백버튼 누르면 HomeFragment로 이동
        homeSearchBinding.toolbarWithoutTitleBackButton.setOnClickListener{
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }

    }
}
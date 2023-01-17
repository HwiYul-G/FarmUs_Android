package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvRecommendFarmForFarmerBinding

class RecRVAdapter(val items : MutableList<String>) : RecyclerView.Adapter<RecRVAdapter.ViewHolder>() {

    private lateinit var recBinding: RvRecommendFarmForFarmerBinding

    override fun getItemCount(): Int {
        return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType : Int) : RecRVAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_recommend_farm_for_farmer, parent)

        //테두리 둥글게 설정
        recBinding.rvRecImg.clipToOutline = true

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecRVAdapter.ViewHolder, position : Int) {
        holder.bindItems(items[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: String){

        }
    }
}
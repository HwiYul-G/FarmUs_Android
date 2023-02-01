package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvLocalFarmBinding
import com.example.farmus_application.ui.home.RVFarmDataModel

class ResultRVAdapter(val items: MutableList<RVFarmDataModel>): RecyclerView.Adapter<ResultRVAdapter.ViewHolder>()  {

    private lateinit var resultBinding : RvLocalFarmBinding

    override fun getItemCount(): Int {

        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultRVAdapter.ViewHolder{

        resultBinding = RvLocalFarmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //테두리 둥글게 설정
        resultBinding.rvFarmImage.clipToOutline = true

        return ViewHolder(resultBinding)
    }

    override fun onBindViewHolder(holder: ResultRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    inner class ViewHolder(binding: RvLocalFarmBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item : RVFarmDataModel){

            //이미지 어떻게 넣을지 수정!!
            resultBinding.rvFarmImage.setImageResource(item.farm_image)
            resultBinding.rvFarmName.text = item.farm_name
            resultBinding.rvFarmSize.text = item.farm_size
            resultBinding.rvFarmPrice.text = item.farm_price
        }
    }
}
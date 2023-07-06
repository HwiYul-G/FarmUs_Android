package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.databinding.RvLocalFarmBinding
import com.example.farmus_application.ui.home.RVFarmDataModel

class FarmRVAdapter: ListAdapter<RVFarmDataModel, FarmRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: RvLocalFarmBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item : RVFarmDataModel){
            // img는 glide로 처리할 것
            Glide.with(binding.root.context)
                .load(item.farm_image)
                .into(binding.rvFarmImage)
            binding.rvFarmName.text = item.farm_name
            binding.rvFarmSize.text = item.farm_size
            binding.rvFarmPrice.text = item.farm_price
            binding.bookMark.setOnClickListener {
                binding.bookMark.isSelected = !binding.bookMark.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmRVAdapter.ViewHolder {

        return ViewHolder(RvLocalFarmBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FarmRVAdapter.ViewHolder, position: Int) {

        holder.bind(currentList[position])

    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<RVFarmDataModel>(){
            override fun areItemsTheSame(
                oldItem: RVFarmDataModel,
                newItem: RVFarmDataModel
            ): Boolean {
               return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RVFarmDataModel,
                newItem: RVFarmDataModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.RvLocalFarmBinding
import com.example.farmus_application.ui.home.RVFarmDataModel

class FarmRVAdapter: ListAdapter<RVFarmDataModel, FarmRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: RvLocalFarmBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item : RVFarmDataModel){
            binding.rvFarmImage.setImageResource(item.farm_image)
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
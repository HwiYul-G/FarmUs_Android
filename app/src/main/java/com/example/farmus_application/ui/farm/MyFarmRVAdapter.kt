package com.example.farmus_application.ui.farm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.RvMyFarmItemBinding

class MyFarmRVAdapter() : ListAdapter<MyFarmDataModel, MyFarmRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding : RvMyFarmItemBinding

    inner class ViewHolder(binding : RvMyFarmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MyFarmDataModel){
            binding.ivItemImg.setImageResource(item.image)
            binding.tvLocation.text = item.location
            binding.tvFarmName.text = item.title
            binding.tvFarmSize.text = item.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvMyFarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<MyFarmDataModel>(){
            override fun areItemsTheSame(
                oldItem: MyFarmDataModel,
                newItem: MyFarmDataModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: MyFarmDataModel,
                newItem: MyFarmDataModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
package com.example.farmus_application.ui.farm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.RvGetFarmItemBinding
import com.example.farmus_application.databinding.RvMyFarmItemBinding

class GetFarmRVAdapter() : ListAdapter<FarmDataModel, GetFarmRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding: RvGetFarmItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : GetFarmRVAdapter.ViewHolder {
        binding = RvGetFarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvItemImg.clipToOutline = true
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    inner class ViewHolder( binding: RvGetFarmItemBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(item : FarmDataModel) {
            binding.rvItemImg.setImageResource(item.image)
            binding.rvItemTitle.text = item.title
            binding.rvItemStartDay.text = item.startDay
            binding.rvItemEndDay.text = item.endDay
        }
    }
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<FarmDataModel>(){
            override fun areItemsTheSame(
                oldItem: FarmDataModel,
                newItem: FarmDataModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: FarmDataModel,
                newItem: FarmDataModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
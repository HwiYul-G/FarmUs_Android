package com.farm.farmus_application.ui.farm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.RvMyFarmItemBinding
import com.farm.farmus_application.model.farm.myfarm.MyFarmItem

class MyFarmRVAdapter(val onClick: (MyFarmItem) -> Unit) : ListAdapter<MyFarmItem, MyFarmRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding : RvMyFarmItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.rv_my_farm_item, parent, false )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ViewHolder(private val binding: RvMyFarmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MyFarmItem){
            binding.myFarmItem = item

            if (item.Picture_url == null) {
                Glide.with(binding.ivItemImg)
                    .load(R.drawable.farm_image_example)
                    .centerCrop()
                    .into(binding.ivItemImg)

            } else {
                Glide.with(binding.ivItemImg)
                    .load(item.Picture_url)
                    .centerCrop()
                    .into(binding.ivItemImg)
            }

            binding.root.setOnClickListener {
                onClick(item)
            }
        }
    }
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<MyFarmItem>(){
            override fun areItemsTheSame(
                oldItem: MyFarmItem,
                newItem: MyFarmItem
            ): Boolean {
                return oldItem.FarmID == newItem.FarmID
            }

            override fun areContentsTheSame(
                oldItem: MyFarmItem,
                newItem: MyFarmItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
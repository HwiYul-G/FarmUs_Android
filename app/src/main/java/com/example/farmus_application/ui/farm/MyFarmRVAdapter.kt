package com.example.farmus_application.ui.farm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvMyFarmItemBinding
import com.example.farmus_application.model.farm.myfarm.MyFarmItem

class MyFarmRVAdapter(val onClick: (MyFarmItem) -> Unit) : ListAdapter<MyFarmItem, MyFarmRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding : RvMyFarmItemBinding
//    private var listener: OnClickListener? = null
//
//    interface OnClickListener {
//        fun onClick(view: View, data: MyFarmDataModel, pos: Int)
//    }
//
//    fun setOnClickListener(listener: OnClickListener) {
//        this.listener = listener
//    }

    inner class ViewHolder(binding : RvMyFarmItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MyFarmItem){
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = RvMyFarmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<MyFarmItem>(){
            override fun areItemsTheSame(
                oldItem: MyFarmItem,
                newItem: MyFarmItem
            ): Boolean {
                return oldItem == newItem
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
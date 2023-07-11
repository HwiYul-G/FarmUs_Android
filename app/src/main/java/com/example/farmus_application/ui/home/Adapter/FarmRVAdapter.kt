package com.example.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.farmus_application.R
import com.example.farmus_application.databinding.RvLocalFarmBinding
import com.example.farmus_application.model.farm.list.ListResult


class FarmRVAdapter : ListAdapter<ListResult, FarmRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: RvLocalFarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ListResult) {
            // img는 glide로 처리할 것
            Glide.with(binding.root.context)
                .load(
                    if (item.Pictures.isNotEmpty()) item.Pictures[0].PictureUrl
                    else R.drawable.farm_image_example
                )
                .into(binding.rvFarmImage)
            binding.rvFarmName.text = item.Name

            val squaredMeter = item.SquaredMeters
            val squaredFeet = squaredMeter * 0.3025
            val itemSize = squaredFeet.toString() + "평 (" + squaredMeter + "㎡)"

            binding.rvFarmSize.text = itemSize
            binding.rvFarmPrice.text = item.Price.toString()

            binding.bookMark.setOnClickListener {
                binding.bookMark.isSelected = !binding.bookMark.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FarmRVAdapter.ViewHolder {

        return ViewHolder(
            RvLocalFarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FarmRVAdapter.ViewHolder, position: Int) {

        holder.bind(currentList[position])

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ListResult>() {
            override fun areItemsTheSame(
                oldItem: ListResult,
                newItem: ListResult
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListResult,
                newItem: ListResult
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
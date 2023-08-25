package com.farm.farmus_application.ui.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.RvLocalFarmBinding
import com.farm.farmus_application.model.farm.search.SearchedFarm


class SearchedFarmRVAdapter : ListAdapter<SearchedFarm, SearchedFarmRVAdapter.ViewHolder>(diffUtil) {

    inner class ViewHolder(private val binding: RvLocalFarmBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchedFarm) {
            Glide.with(binding.root.context)
                .load(
                    item.Picture_url ?: R.drawable.farm_image_example
                )
                .into(binding.rvFarmImage)
            binding.rvFarmName.text = item.Name

            val squaredMeter = item.SquaredMeters
            val squaredFeet = squaredMeter * 0.3025
            val itemSize = squaredFeet.toString() + "평 (" + squaredMeter + "㎡)"

            binding.rvFarmSize.text = itemSize
            binding.rvFarmPrice.text = item.Price.toString()

            binding.bookMark.setOnClickListener {
                // TODO : 북마크에 대한 부분은 논의 필요! FarmRVadapter도 마찬가지 (우선 날라오는 데이터 값이 없음)
                binding.bookMark.isSelected = !binding.bookMark.isSelected
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchedFarmRVAdapter.ViewHolder {

        return ViewHolder(
            RvLocalFarmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SearchedFarmRVAdapter.ViewHolder, position: Int) {

        holder.bind(currentList[position])

    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SearchedFarm>() {
            override fun areItemsTheSame(
                oldItem: SearchedFarm,
                newItem: SearchedFarm
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: SearchedFarm,
                newItem: SearchedFarm
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}
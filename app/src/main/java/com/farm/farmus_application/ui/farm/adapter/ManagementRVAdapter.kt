package com.farm.farmus_application.ui.farm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.farm.farmus_application.R
import com.farm.farmus_application.databinding.ItemManagementCalnedarBinding
import com.farm.farmus_application.model.reserve.unbookable.UnBookableResult

class ManagementRVAdapter: ListAdapter<UnBookableResult, ManagementRVAdapter.ManagementViewHolder>(diffUtil) {

    private lateinit var binding: ItemManagementCalnedarBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManagementViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_management_calnedar, parent, false)
        return ManagementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ManagementViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ManagementViewHolder(private val binding: ItemManagementCalnedarBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UnBookableResult) {
            binding.unAvailableDate = item
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<UnBookableResult>() {
            override fun areItemsTheSame(
                oldItem: UnBookableResult,
                newItem: UnBookableResult
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: UnBookableResult,
                newItem: UnBookableResult
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
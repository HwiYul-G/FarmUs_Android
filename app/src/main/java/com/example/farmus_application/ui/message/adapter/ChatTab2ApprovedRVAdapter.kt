package com.example.farmus_application.ui.message.adapter

import android.graphics.Paint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.FragmentChatTab2Binding
import com.example.farmus_application.databinding.RvChatTab1ItemBinding
import com.example.farmus_application.databinding.RvChatTab2ApprovedItemBinding
import com.example.farmus_application.databinding.RvChatTab2ItemBinding
import com.example.farmus_application.ui.message.RVTab1DataModel
import com.example.farmus_application.ui.message.RVTab2DataModel

class ChatTab2ApprovedRVAdapter() :
    ListAdapter<RVTab2DataModel, ChatTab2ApprovedRVAdapter.ViewHolder>(diffUtil) {

    private lateinit var binding: RvChatTab2ApprovedItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatTab2ApprovedRVAdapter.ViewHolder {
        binding =
            RvChatTab2ApprovedItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.rvChatHistory.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        //분양 신청 내역 밑줄
        binding.rvChatImg.clipToOutline = true

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatTab2ApprovedRVAdapter.ViewHolder, position: Int) {
        holder.bindItems(currentList[position])
    }

    inner class ViewHolder(binding: RvChatTab2ApprovedItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: RVTab2DataModel) {
            binding.rvChatImg.setImageResource(item.farm_image)
            binding.rvChatName.text = item.farm_name
            binding.rvChatPeriodDay.text = item.farm_period
        }
    }

    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<RVTab2DataModel>(){
            override fun areItemsTheSame(
                oldItem: RVTab2DataModel,
                newItem: RVTab2DataModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: RVTab2DataModel,
                newItem: RVTab2DataModel
            ): Boolean {
                return oldItem.farm_name == newItem.farm_name
            }

        }
    }
}
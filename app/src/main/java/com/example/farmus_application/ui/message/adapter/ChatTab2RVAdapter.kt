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
import com.example.farmus_application.databinding.RvChatTab2ItemBinding
import com.example.farmus_application.ui.message.RVTab1DataModel
import com.example.farmus_application.ui.message.RVTab2DataModel

class ChatTab2RVAdapter() : ListAdapter<RVTab2DataModel, ChatTab2RVAdapter.ViewHolder>(diffUtil) {

    private lateinit var chatTab2Binding: RvChatTab2ItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatTab2RVAdapter.ViewHolder {
        chatTab2Binding =
            RvChatTab2ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        chatTab2Binding.rvChatHistory.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        //분양 신청 내역 밑줄
        chatTab2Binding.rvChatImg.clipToOutline = true

        return ViewHolder(chatTab2Binding)
    }

    override fun onBindViewHolder(holder: ChatTab2RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(currentList[position])
    }

    inner class ViewHolder(binding: RvChatTab2ItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: RVTab2DataModel) {
            chatTab2Binding.rvChatImg.setImageResource(item.farm_image)
            chatTab2Binding.rvChatName.text = item.farm_name
            chatTab2Binding.rvChatPeriodDay.text = item.farm_period
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
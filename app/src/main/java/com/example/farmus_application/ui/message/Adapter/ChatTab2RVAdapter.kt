package com.example.farmus_application.ui.message.Adapter

import android.graphics.Paint
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.FragmentChatTab2Binding
import com.example.farmus_application.databinding.RvChatTab1ItemBinding
import com.example.farmus_application.databinding.RvChatTab2ItemBinding
import com.example.farmus_application.ui.message.RVTab2DataModel

class ChatTab2RVAdapter(val items: MutableList<RVTab2DataModel>): RecyclerView.Adapter<ChatTab2RVAdapter.ViewHolder>() {

    private lateinit var chatTab2Binding: RvChatTab2ItemBinding

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatTab2RVAdapter.ViewHolder {
        chatTab2Binding = RvChatTab2ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        chatTab2Binding.rvChatHistory.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        //분양 신청 내역 밑줄
        chatTab2Binding.rvChatImg.clipToOutline = true

        return ViewHolder(chatTab2Binding)
    }

    override fun onBindViewHolder(holder: ChatTab2RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    inner class ViewHolder(binding: RvChatTab2ItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item: RVTab2DataModel){
            chatTab2Binding.rvChatImg.setImageResource(item.farm_image)
            chatTab2Binding.rvChatName.text = item.farm_name
            chatTab2Binding.rvChatPeriodDay.text = item.farm_period
        }
    }
}
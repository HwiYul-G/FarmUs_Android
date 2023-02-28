package com.example.farmus_application.ui.message.Adapter


import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farmus_application.databinding.RvChatTab1ItemBinding
import com.example.farmus_application.ui.message.*


class ChatTab1RVAdapter(val items: MutableList<RVTab1DataModel>): RecyclerView.Adapter<ChatTab1RVAdapter.ViewHolder>(){

    private lateinit var chatTab1Binding: RvChatTab1ItemBinding

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        chatTab1Binding = RvChatTab1ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        //텍스트 밑줄
        chatTab1Binding.rvChatHistory.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        //이미지 테두리 둥글게
        chatTab1Binding.rvChatImg.clipToOutline = true

        chatTab1Binding.rvChatHistory.setOnClickListener {

        }

        return ViewHolder(chatTab1Binding)
    }

    override fun onBindViewHolder(holder: ChatTab1RVAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])

    }

    inner class ViewHolder(binding: RvChatTab1ItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItems(item: RVTab1DataModel){
            chatTab1Binding.rvChatImg.setImageResource(item.farm_image)
            chatTab1Binding.rvChatName.text = item.farm_name
            chatTab1Binding.rvChatPeriodDay.text = item.farm_period
        }
    }
}
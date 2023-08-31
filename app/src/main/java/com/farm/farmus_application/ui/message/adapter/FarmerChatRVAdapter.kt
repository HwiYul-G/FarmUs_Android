package com.farm.farmus_application.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farm.farmus_application.databinding.ItemContainerDateBinding
import com.farm.farmus_application.databinding.ItemContainerReceivedMessageBinding
import com.farm.farmus_application.databinding.ItemContainerSentMessageBinding
import com.farm.farmus_application.ui.message.ChatMessage

class FarmerChatRVAdapter(
    private val chatMessages: List<ChatMessage>,
    private val senderId: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    enum class ViewType {
        VIEW_TYPE_SENT, VIEW_TYPE_RECEIVED, VIEW_TYPE_DAY
    }

    override fun getItemViewType(position: Int): Int {
        val chatMessage = chatMessages[position]
        return when {
            chatMessage.senderId == senderId -> {
                ClientChatRVAdapter.ViewType.VIEW_TYPE_SENT.ordinal
            }

            chatMessage.senderId == "0" && chatMessage.receiverId == "0" -> {
                ClientChatRVAdapter.ViewType.VIEW_TYPE_DAY.ordinal
            }

            else -> {
                ClientChatRVAdapter.ViewType.VIEW_TYPE_RECEIVED.ordinal
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (ClientChatRVAdapter.ViewType.values()[viewType]) {
            ClientChatRVAdapter.ViewType.VIEW_TYPE_SENT -> {
                val binding = ItemContainerSentMessageBinding.inflate(layoutInflater, parent, false)
                SentMessageViewHolder(binding)
            }

            ClientChatRVAdapter.ViewType.VIEW_TYPE_RECEIVED -> {
                val binding =
                    ItemContainerReceivedMessageBinding.inflate(layoutInflater, parent, false)
                ReceivedMessageViewHolder(binding)
            }

            ClientChatRVAdapter.ViewType.VIEW_TYPE_DAY -> {
                val binding = ItemContainerDateBinding.inflate(layoutInflater, parent, false)
                DayViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return chatMessages.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chatMessage = chatMessages[position]
        when (ClientChatRVAdapter.ViewType.values()[getItemViewType(position)]) {
            ClientChatRVAdapter.ViewType.VIEW_TYPE_SENT -> (holder as SentMessageViewHolder).setData(
                chatMessage
            )

            ClientChatRVAdapter.ViewType.VIEW_TYPE_RECEIVED -> (holder as ReceivedMessageViewHolder).setData(
                chatMessage
            )

            ClientChatRVAdapter.ViewType.VIEW_TYPE_DAY -> (holder as DayViewHolder).setData(
                chatMessage
            )
        }
    }

    class SentMessageViewHolder(private val binding: ItemContainerSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(chatMessage: ChatMessage) {
            binding.textMessage.text = chatMessage.message
            binding.textDateTime.text = chatMessage.dateTime
        }
    }

    class ReceivedMessageViewHolder(private val binding: ItemContainerReceivedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(chatMessage: ChatMessage) {
            binding.textMessage.text = chatMessage.message
            binding.textDateTime.text = chatMessage.dateTime
        }
    }

    class DayViewHolder(private val binding: ItemContainerDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(chatMessage: ChatMessage) {
            binding.tvDate.text = chatMessage.message
        }
    }

}
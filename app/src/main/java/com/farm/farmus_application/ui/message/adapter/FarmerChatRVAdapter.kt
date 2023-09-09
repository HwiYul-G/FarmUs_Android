package com.farm.farmus_application.ui.message.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.farm.farmus_application.databinding.ItemContainerReceivedMessageBinding
import com.farm.farmus_application.databinding.ItemContainerSentMessageBinding
import com.farm.farmus_application.repository.UserPrefsStorage
import com.farm.farmus_application.ui.message.ChatMessage
import com.farm.farmus_application.utilities.JWTUtils

class FarmerChatRVAdapter(
    private val chatMessages: List<ChatMessage>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val jwtToken = UserPrefsStorage.accessToken
    private val name = JWTUtils.decoded(jwtToken.toString())?.tokenBody?.name ?: ""

    override fun getItemViewType(position: Int): Int {
        val chatMessage = chatMessages[position]
        return when (chatMessage.name) {
            name -> {
                ClientChatRVAdapter.ViewType.VIEW_TYPE_SENT.ordinal
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
//            ClientChatRVAdapter.ViewType.VIEW_TYPE_DAY -> {
//                val binding = ItemContainerDateBinding.inflate(layoutInflater, parent, false)
//                DayViewHolder(binding)
//            }
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

//            ClientChatRVAdapter.ViewType.VIEW_TYPE_DAY -> (holder as DayViewHolder).setData(
//                chatMessage
//            )
        }
    }

    class SentMessageViewHolder(private val binding: ItemContainerSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(chatMessage: ChatMessage) {
//            binding.textMessage.text = chatMessage.message
//            binding.textDateTime.text = chatMessage.dateTime
            binding.chatModel = chatMessage
        }
    }

    class ReceivedMessageViewHolder(private val binding: ItemContainerReceivedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(chatMessage: ChatMessage) {
//            binding.textMessage.text = chatMessage.message
//            binding.textDateTime.text = chatMessage.dateTime
            binding.chatModel = chatMessage
        }
    }

//    class DayViewHolder(private val binding: ItemContainerDateBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//        fun setData(chatMessage: ChatMessage) {
//            binding.tvDate.text = chatMessage.message
//        }
//    }

}
package com.farm.farmus_application.ui.message

// UI만 확인용으로 임시 구현 (나중에 통신부와 맞추어서 변경해야함)
data class ChatMessage(
    val senderId : String,
    val receiverId : String,
    val message : String,
    val dateTime : String
)

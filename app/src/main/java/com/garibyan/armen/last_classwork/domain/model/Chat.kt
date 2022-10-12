package com.garibyan.armen.last_classwork.domain.model

data class Chat(
    val id: Int?,
    val email: String?,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val messageType: String?,
    val lastMessage: String?,
    val unreadMessage: Int?,
    val isTyping: Boolean?,
    val updatedDate: String?
)


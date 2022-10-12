package com.garibyan.armen.last_classwork.data.remote.mappers

import com.garibyan.armen.last_classwork.common.extentions.convertToDate
import com.garibyan.armen.last_classwork.data.remote.dto.ChatDto
import com.garibyan.armen.last_classwork.domain.model.Chat

fun ChatDto.toChat() = Chat(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar,
    messageType = messageType,
    lastMessage = lastMessage,
    unreadMessage = unreadMessage,
    isTyping = isTyping,
    updatedDate = updatedDate?.convertToDate()
)

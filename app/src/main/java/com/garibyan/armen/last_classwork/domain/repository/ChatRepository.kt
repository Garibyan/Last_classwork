package com.garibyan.armen.last_classwork.domain.repository

import com.garibyan.armen.last_classwork.common.Resource
import com.garibyan.armen.last_classwork.data.remote.dto.ChatDto
import com.garibyan.armen.last_classwork.domain.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getAllChats(): Flow<Resource<List<Chat>>>

}
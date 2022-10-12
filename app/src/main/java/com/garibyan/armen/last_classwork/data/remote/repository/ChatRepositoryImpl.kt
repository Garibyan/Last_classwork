package com.garibyan.armen.last_classwork.data.remote.repository

import com.garibyan.armen.last_classwork.common.BaseRepository
import com.garibyan.armen.last_classwork.common.Resource
import com.garibyan.armen.last_classwork.common.mapSuccess
import com.garibyan.armen.last_classwork.data.remote.ChatService
import com.garibyan.armen.last_classwork.data.remote.mappers.toChat
import com.garibyan.armen.last_classwork.domain.model.Chat
import com.garibyan.armen.last_classwork.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
    private val baseRepository: BaseRepository
    ): ChatRepository {

    override suspend fun getAllChats(): Flow<Resource<List<Chat>>> {
        return flow {
            emit(Resource.Loader)
            emit(baseRepository.saveApiCall { chatService.getAllChats() }.mapSuccess { it.toChat() })
        }
    }

}
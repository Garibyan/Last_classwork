package com.garibyan.armen.last_classwork.domain.usecase

import com.garibyan.armen.last_classwork.domain.repository.ChatRepository
import javax.inject.Inject

class GetAllChatsUseCase @Inject constructor(private val chatRepository: ChatRepository) {

    suspend operator fun invoke() = chatRepository.getAllChats()

}
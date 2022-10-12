package com.garibyan.armen.last_classwork.data.remote

import com.garibyan.armen.last_classwork.data.remote.dto.ChatDto
import retrofit2.Response
import retrofit2.http.GET

interface ChatService {

    @GET("80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf")
    suspend fun getAllChats(): Response<List<ChatDto>>
}
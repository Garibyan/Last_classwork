package com.garibyan.armen.last_classwork.di

import com.garibyan.armen.last_classwork.common.BaseRepository
import com.garibyan.armen.last_classwork.data.remote.repository.ChatRepositoryImpl
import com.garibyan.armen.last_classwork.domain.repository.ChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChatRepositoryImpl(chatRepositoryImpl: ChatRepositoryImpl): ChatRepository

}
package com.garibyan.armen.last_classwork.di

import com.garibyan.armen.last_classwork.common.BaseRepository
import com.garibyan.armen.last_classwork.data.remote.ChatService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/v3/")
            .addConverterFactory(
                MoshiConverterFactory.create(
                Moshi.Builder().add(
                    KotlinJsonAdapterFactory()
                ).build()
            ))
            .build()
    }

    @Provides
    @Singleton
    fun provideChatService(retrofit: Retrofit): ChatService{
        return retrofit.create(ChatService::class.java)
    }

}
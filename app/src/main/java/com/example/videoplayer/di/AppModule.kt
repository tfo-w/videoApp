package com.example.videoplayer.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.videoplayer.network.VideoApiService
import com.example.videoplayer.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.vk.com/method/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideVideoRepository(
        apiService: VideoApiService,
        sharedPreferences: SharedPreferences // Добавьте параметр
    ): VideoRepository {
        return VideoRepository(apiService, sharedPreferences) // Передайте его
    }
}
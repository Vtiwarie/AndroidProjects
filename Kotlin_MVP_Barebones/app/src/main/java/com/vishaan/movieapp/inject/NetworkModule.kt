package com.vishaan.movieapp.inject

import com.vishaan.movieapp.BuildConfig
import com.vishaan.movieapp.api.serialize.MovieDeserializer
import com.vishaan.movieapp.data.entity.Search
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .create()
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    companion object {
        const val CONNECTION_TIMEOUT = 30L
        const val DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }

    private inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type
}
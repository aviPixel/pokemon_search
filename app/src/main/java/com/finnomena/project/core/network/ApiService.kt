package com.finnomena.project.core.network

import com.finnomena.project.candidate.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    companion object {

        private fun BASE_URL() = "https://pokeapi.co/api/v2/"

        private val interceptor: Interceptor
            get() = HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
            }

        private val client: OkHttpClient
            get() = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()

        private val gson = GsonBuilder()
                .setLenient()
                .create()

        fun initEndPoint(): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(BASE_URL())
                client(client)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }

        fun initEndPoint(url: String): Retrofit {
            return Retrofit.Builder().apply {
                baseUrl(url)
                client(client)
                addConverterFactory(GsonConverterFactory.create(gson))
            }.build()
        }
    }

}
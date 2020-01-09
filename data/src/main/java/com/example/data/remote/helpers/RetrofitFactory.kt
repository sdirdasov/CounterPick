package com.example.data.remote.helpers

import com.example.data.remote.services.HeroesService
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory {

    companion object {
        val baseUrl = "https://api.opendota.com/"

        private fun getOkHttpInstance(): OkHttpClient {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
        }

        @UnstableDefault
        private fun getRetrofitClient(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpInstance())
                .addConverterFactory(Json.nonstrict.asConverterFactory(contentType = "application/json".toMediaTypeOrNull()!!))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        @UnstableDefault
        fun getHeroesService() = RetrofitFactory.getRetrofitClient().create(HeroesService::class.java)
    }
}
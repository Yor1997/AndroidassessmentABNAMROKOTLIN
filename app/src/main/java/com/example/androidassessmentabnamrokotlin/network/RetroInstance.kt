package com.example.androidassessmentabnamrokotlin.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetroInstance {

    companion object {
        val baseURL = "https://api.github.com/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        }
    }
}
package com.example.androidassessmentabnamrokotlin.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

// https://api.github.com/users/abnamrocoesd/repos?page=1&per_page=10

interface RetroService {
    @GET("users/abnamrocoesd/repos")
    suspend fun getDataFromAPI(
        @Query("page") page: Int,
        @Query("per_page") per_page: Int
    ): Response<List<RepositoryData>>
}
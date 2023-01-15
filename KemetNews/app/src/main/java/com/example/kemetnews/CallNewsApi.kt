package com.example.kemetnews

import retrofit2.http.GET
import com.example.kemetnews.Models.ApiResponse
import retrofit2.Call
import retrofit2.http.Query

// Api Interface
interface CallNewsApi {
    @GET("news")
    fun getNews(
        @Query("apikey") apiKey: String?,
        @Query("q") query: String?,
        @Query("country") country: String?
    ): Call<ApiResponse?>?

    @GET("news")
    fun getCategoryNews(
        @Query("apikey") apiKey: String?,
        @Query("language") language: String?,
        @Query("category") category: String?
    ): Call<ApiResponse?>?
}
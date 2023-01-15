package com.example.kemetnews

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestManager {
    private var retrofit: Retrofit? = null
    @JvmStatic
    val apiRequest: CallNewsApi
        get() {
            if (retrofit == null) {
                retrofit = Retrofit.Builder().baseUrl("https://newsdata.io/api/1/")
                    .addConverterFactory(GsonConverterFactory.create()).build()
            }
            return retrofit!!.create(CallNewsApi::class.java)
        }
}
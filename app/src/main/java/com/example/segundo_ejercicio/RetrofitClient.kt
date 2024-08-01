package com.example.segundo_ejercicio

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://32eb0b3c80fe488d9983eb91a98d0679.api.mockbin.io/"

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
    val Apidepartamento: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://b3584ce8099a4587876aa86e883de221.api.mockbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
    val careerApi: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://c30ea15077ea44a1b36047e374ef1b62.api.mockbin.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

}

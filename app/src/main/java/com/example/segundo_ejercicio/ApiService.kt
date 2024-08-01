package com.example.segundo_ejercicio

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("/")
    fun getStates(): Call<StateResponse>

    @GET("/departamentos")
    fun getDepartments(): Call<DepartmentResponse>

    @GET("/carreras")
    fun getCareers(): Call<CareerResponse>
}

package com.example.myapplication.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataRepository {

    fun create(): ApiInterface {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
            .build()
        return retrofit.create(ApiInterface::class.java)
    }
}
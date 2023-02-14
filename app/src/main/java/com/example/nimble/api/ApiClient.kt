package com.example.nimble.api

import com.example.nimble.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient{
    var retrofit: Retrofit? = null

   fun getApiClient(): Retrofit?{
        if (retrofit == null){
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }

}
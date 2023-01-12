package com.example.nimble

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

const val BASE_URL = "http://0.0.0.0:8080"

interface ApiRequest {
    @POST("/register")
    @FormUrlEncoded
    suspend fun singnup(@Field("email") email: String, @Field("password") password: String, @Field("firstName") firstName: String, @Field("lastName") lastName: String): Response<Auth>
}
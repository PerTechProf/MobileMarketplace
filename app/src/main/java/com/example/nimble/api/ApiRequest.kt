package com.example.nimble.api

import com.example.nimble.Auth
import com.squareup.moshi.JsonClass
import retrofit2.Call
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

const val BASE_URL = "http://192.168.0.169:8080/"

@JsonClass(generateAdapter = true)
data class RegisterReceiveRemote(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String
)

@JsonClass(generateAdapter = true)
data class LoginReceiveRemote(
    val email: String,
    val password: String
)


@JsonClass(generateAdapter = true)
data class CatalogGoods(
    val name: String,
    val price: Double,
    val logo : String,
    val description : String
)


interface ApiRequest {
    @POST("register")
    suspend fun signup(@Body data: RegisterReceiveRemote): Response<Auth>


    @POST("login")
    suspend fun signin(@Body data: LoginReceiveRemote): Response<Auth>


    @GET("goods/catalog")
    fun getGoods(): Call<List<CatalogGoods>>
}


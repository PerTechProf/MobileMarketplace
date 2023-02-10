package com.example.nimble.api

import com.example.nimble.BASE_URL
import com.example.nimble.user.Auth
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


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
    val logo: String,
    val description: String
)

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiRequest {
    @POST("register")
    suspend fun signup(@Body data: RegisterReceiveRemote): Response<Auth>


    @POST("login")
    suspend fun signin(@Body data: LoginReceiveRemote): Response<Auth>


    @GET("goods/catalog")
    fun getGoods(): Call<List<CatalogGoods>>

}

object Api {
    val retrofitService: ApiRequest by lazy{retrofit.create(ApiRequest::class.java)}
}

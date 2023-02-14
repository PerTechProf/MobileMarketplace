package com.example.nimble.api

import com.example.nimble.BASE_URL
import com.example.nimble.user.Auth
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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
data class Good(
    val name : String,
    val price: Double,
    val manufacturer : String,
    val logo : String,
    val grade : Double,
    val description : String,
    val specification : String,
    val availability : Int,
)

@JsonClass(generateAdapter = true)
data class ListResponse<T>(
    val items: List<T>
)

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()

interface ApiRequest {
    @POST("register")
    suspend fun signup(@Body data: RegisterReceiveRemote): Response<Auth>


    @POST("login")
    suspend fun signin(@Body data: LoginReceiveRemote): Response<Auth>


    @GET("goods")
    suspend fun getGoods(): Response<ListResponse<Good>>


}

object Api {
    val retrofitService: ApiRequest by lazy{retrofit.create(ApiRequest::class.java)}
}


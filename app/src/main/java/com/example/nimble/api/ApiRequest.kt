package com.example.nimble.api

import com.example.nimble.BASE_URL
import com.example.nimble.user.Auth
import com.squareup.moshi.JsonClass
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


val api: ApiRequest = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create().asLenient())
    .build()
    .create(ApiRequest::class.java)

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
    val id: String,
    val name: String,
    val price: Double,
    val manufacturer: String,
    val logo: String,
    val grade: Double,
    val description: String,
    val specification: String,
    var availability: Int,
    val vendorCode: String
)

@JsonClass(generateAdapter = true)
data class ListResponse<T>(
    val items: List<T>
)

@JsonClass(generateAdapter = true)
data class UserOut(
    val email: String,
    val firstName: String,
    val lastName: String
)

@JsonClass(generateAdapter = true)
data class UserAddress(
    val home_address: String,
    val house_address: String,
    val city: String
)

@JsonClass(generateAdapter = true)
data class Carts(
    val product: Good,
    val quantity: Int,
)

@JsonClass(generateAdapter = true)
data class CartItem(
    val userId: String,
    val productId: String,
)

interface ApiRequest {
    @POST("register")
    suspend fun signup(@Body data: RegisterReceiveRemote): Response<Auth>


//    @POST("login")
//    suspend fun signin(@Body data: LoginReceiveRemote): Response<Auth>

    @POST("login")
    suspend fun signin(@Body data: LoginReceiveRemote): Response<Auth>


    @GET("goods")
    suspend fun getGoods(): Response<ListResponse<Good>>


    @GET("user/{id}")
    suspend fun user() : Response<ListResponse<UserOut>>

    @POST("register/address")
    suspend fun registerAddress(@Body data: UserAddress)

    @POST("cart/")
    suspend fun addCartItem(@Body data : CartItem)

    @GET("cart/{userId}")
    suspend fun getCartItems(userId: String) : Response<ListResponse<Carts>>

}



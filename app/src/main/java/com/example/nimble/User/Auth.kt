package com.example.nimble.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Auth(
    val userId: String,
    val token: String
)

var tokenUser : String? = null
var userId: String? = null


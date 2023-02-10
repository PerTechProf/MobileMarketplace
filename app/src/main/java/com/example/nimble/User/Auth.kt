package com.example.nimble.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Auth(
    val token: String
)

var tokenUser : String? = null
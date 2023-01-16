package com.example.nimble

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Auth(
    val token: String
)

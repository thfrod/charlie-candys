package dev.thfrod.charliecandys.responses

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: T
)

data class LoginResponse (
    @SerializedName("token")
    val token: String
)
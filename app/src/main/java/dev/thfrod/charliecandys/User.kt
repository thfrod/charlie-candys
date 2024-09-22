package dev.thfrod.charliecandys

import kotlinx.serialization.Serializable

@Serializable
data class User (
    val email: String,
    val password: String,
)

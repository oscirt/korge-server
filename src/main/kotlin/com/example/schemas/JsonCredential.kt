package com.example.schemas

@kotlinx.serialization.Serializable
data class JsonCredential(
    val username: String,
    val password: String
)

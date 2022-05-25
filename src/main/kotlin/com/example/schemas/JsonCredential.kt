package com.example.schemas

import kotlinx.serialization.*

@Serializable
data class JsonCredential(
    val username: String,
    val password: String
)
package com.example.schemas

@kotlinx.serialization.Serializable
data class Point(
    val name: String,
    val x: Double,
    val y: Double
)
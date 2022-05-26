package com.example.schemas

import java.util.concurrent.atomic.AtomicInteger

val wolfAtomicInt = AtomicInteger(-1)

data class Wolf(
    var x: Double,
    var y: Double,
    var direction: Int,
    val id: Int = wolfAtomicInt.incrementAndGet()
)
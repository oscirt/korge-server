package com.example.schemas

import io.ktor.websocket.*
import java.util.concurrent.atomic.AtomicInteger

data class Connection(
    var session: DefaultWebSocketSession?
) {
    var json = ""
    var point = Point(
        "default",
        0.0,
        0.0
    )
}
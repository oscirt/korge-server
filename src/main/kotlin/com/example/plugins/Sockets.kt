package com.example.plugins

import com.example.connections
import com.example.schemas.Connection
import com.example.serialization.Serialization.getJsonPoint
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/game") {
            val thisConnection = Connection(this)
            try {
                connections.asSequence().filter {
                    it.value != null
                }.forEach {
//                    send(Json.encodeToString(it.value?.point))
                    send(it.value?.json!!)
                }
                for (frame in incoming) {
                    when (frame) {
                        is Frame.Text -> {
                            val text = frame.readText()
                            if (text[0] != '{') {
                                connections.asSequence().filter {
                                    it.value != null && it.value != thisConnection
                                }.forEach {
                                    println("Send to ${it.value?.point?.name}")
                                    it.value?.session?.send(text)
                                }
                            } else {
//                            val point = Json.decodeFromString<Point>(text)
                                val point = getJsonPoint(text)
                                println(point)
                                connections[point.name] = thisConnection
                                connections[point.name]?.json = text
                                connections[point.name]?.point = point
                                connections.asSequence().filter {
                                    it.value != null && it.key != point.name
                                }.forEach {
                                    println("Send to ${it.value?.point?.name}")
                                    it.value?.session?.send(text)
                                }
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                println(e.localizedMessage)
            } finally {
                connections.remove(thisConnection.point.name)
                println("Removing ${thisConnection.point.name}")
            }
        }
    }
}

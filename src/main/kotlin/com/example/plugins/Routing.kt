package com.example.plugins

import com.example.connections
import com.example.database
import com.example.schemas.JsonCredential
import com.example.schemas.Users
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import org.ktorm.dsl.from
import org.ktorm.dsl.insert
import org.ktorm.dsl.select

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("$connections")
        }
    }

    routing {
        post("/login") {
            val jsonCredential = call.receive<JsonCredential>()
            for (row in database.from(Users).select()) {
                if (row[Users.username] == jsonCredential.username &&
                        row[Users.password] == jsonCredential.password) {
                    connections[jsonCredential.username] = null
                    call.respond(HttpStatusCode.OK, "User found")
                }
            }
            call.respond(HttpStatusCode.NotFound, "User not found")
        }
    }

    routing {
        post("/register") {
            val text = call.receiveText()
            println(text)
//            val jsonCredential = call.receive<JsonCredential>()
//            for (row in database.from(Users).select()) {
//                if (row[Users.username] == jsonCredential.username) return@post
//            }
//            database.insert(Users) {
//                set(it.username, jsonCredential.username)
//                set(it.password, jsonCredential.password)
//            }
//            call.respond(HttpStatusCode.OK,"Values inserted")
        }
    }
}

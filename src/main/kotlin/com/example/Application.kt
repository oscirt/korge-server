package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.schemas.Connection
import com.example.schemas.JsonCredential
import com.example.schemas.Users
import io.ktor.websocket.*
import org.ktorm.database.Database
import org.ktorm.dsl.from
import org.ktorm.dsl.select

val database = Database.connect(
    url = "jdbc:postgresql://ec2-3-228-235-79.compute-1.amazonaws.com:5432/df1o9m77okknh2?sslmode=require" +
            "&user=uhcekrgrjnvrcn&password=6fcacaebd4e28eadb311f617a5ad59407a0fc7d909857f72468915e66497977c",
    driver = "org.postgresql.Driver",
)

lateinit var connections: HashMap<String, Connection?>

fun main() {
    connections = HashMap()
    embeddedServer(Netty, port = 8080) {
        configureRouting()
        configureSecurity()
        configureSerialization()
        configureSockets()
    }.start(wait = true)
}

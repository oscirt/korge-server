package com.example.serialization

import com.example.schemas.JsonCredential
import com.example.schemas.Point

object Serialization {
    fun getJsonCredential(json: String) : JsonCredential {
        val username = json.substring(13 until json.indexOf("\",\""))
        val password = json.substring(json.indexOf("\",\"")+14 until json.indexOf("\"}"))
        return JsonCredential(username, password)
    }
    fun getJsonPoint(json: String) : Point {
        val name = json.substring(9 until json.indexOf("\",\""))
        val x = json.substring(json.indexOf("\"x\"")+4 until json.indexOf(",\"y\"")).toDouble()
        val y = json.substring(json.indexOf("\"y\"")+4 until json.indexOf('}')).toDouble()
        return Point(name, x, y)
    }
}
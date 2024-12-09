package com.iu6_mamykin.wonderpicturesserver.controllers

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PictureController {
    private val client = OkHttpClient()

    private val pictures = mapOf(
        "Лисы" to "https://randomfox.ca/floof/",
        "Кофе" to "https://coffee.alexflipnote.dev/random.json",
        "Утки" to "https://random-d.uk/api/random"
    )

    @GetMapping("/get-picture")
    fun getPicture(@RequestParam theme: String): Map<String, String> {
        println("Received request for theme: $theme") // Log to server console
        val request = pictures[theme]?.let {
            Request.Builder()
                .url(it)
                .get()
                .build()
        }

        val response = request?.let { client.newCall(it).execute() }
        if (!response!!.isSuccessful) {
            throw Exception("Failed to fetch data from API: ${response.code}")
        }
        val responseBody = response.body?.string() ?: throw Exception("Empty response body")
        println("API Response Body: $responseBody")
        // Распарсим JSON и извлечем значение imageUrl
        val jsonObject = JSONObject(responseBody)
        val imageUrl = when (theme) {
            "Лисы"-> jsonObject.getString("image")
            "Кофе" -> jsonObject.getString("file")
            "Утки"  -> jsonObject.getString("url")
            else -> jsonObject.getString("image")
        } // Ссылка на изображение
        // Возвращаем результат клиенту
        return mapOf(
            "url" to imageUrl
        )
    }
}

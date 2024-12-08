package com.iu6_mamykin.wonderpicturesserver.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PictureController {

    private val pictures = mapOf(
        "nature" to "https://example.com/nature.jpg",
        "space" to "https://example.com/space.jpg",
        "animals" to "https://example.com/animals.jpg",
        "landscapes" to "https://example.com/landscapes.jpg",
        "cities" to "https://example.com/cities.jpg"
    )

    @GetMapping("/get-picture")
    fun getPicture(@RequestParam theme: String): Map<String, String> {
        val pictureUrl = pictures[theme] ?: "https://example.com/default.jpg"
        return mapOf("url" to pictureUrl)
    }
}

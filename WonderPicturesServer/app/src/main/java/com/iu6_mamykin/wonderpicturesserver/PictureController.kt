package com.iu6_mamykin.wonderpicturesserver.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PictureController {

    private val pictures = mapOf(
        "Природа" to "https://i.pinimg.com/736x/0b/7b/a5/0b7ba5a687ac4e3820ba3a556b9a123c.jpg",
        "Космос" to "https://i.pinimg.com/originals/d9/b8/19/d9b819ec8357a7078c4adb7080544f26.jpg",
        "Животные" to "https://i.pinimg.com/736x/4d/94/fd/4d94fd6f94146da9766652549a63334e.jpg",
        "Пейзажи" to "https://www.zastavki.com/pictures/originals/2014/Nature___Clouds_Clouds_in_the_mountains_089495_.jpg",
        "Города" to "https://avatars.dzeninfra.ru/get-zen_doc/271828/pub_660fad65abb34d38e26f9e74_660fad9755f61733df961e56/scale_1200"
    )

    @GetMapping("/get-picture")
    fun getPicture(@RequestParam theme: String): Map<String, String> {
        println("Received request for theme: $theme") // Log to server console
        val pictureUrl = pictures[theme] ?: "https://example.com/default.jpg"
        return mapOf("url" to pictureUrl)
    }
}

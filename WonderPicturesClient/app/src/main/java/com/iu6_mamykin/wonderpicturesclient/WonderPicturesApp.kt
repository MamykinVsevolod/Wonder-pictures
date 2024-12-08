package com.iu6_mamykin.wonderpicturesclient

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.iu6_mamykin.wonderpicturesclient.data.createApi
import com.iu6_mamykin.wonderpicturesclient.utils.downloadImage
import kotlinx.coroutines.launch

@Composable
fun WonderPicturesApp() {
    val api = createApi()
    var selectedTheme by remember { mutableStateOf<String?>(null) }
    var pictureUrl by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope() // Для запуска корутин

    WonderPicturesScreen(
        themes = listOf("nature", "space", "animals", "landscapes", "cities"),
        selectedTheme = selectedTheme,
        onThemeSelect = { theme ->
            selectedTheme = theme
            isLoading = true
            /// Запускаем корутину через rememberCoroutineScope
            coroutineScope.launch {
                try {
                    val response = api.getPicture(theme)
                    pictureUrl = response.url
                } catch (e: Exception) {
                    // Обрабатываем ошибки запроса
                    e.printStackTrace()
                    pictureUrl = null
                } finally {
                    isLoading = false
                }
            }
        },
        pictureUrl = pictureUrl,
        isLoading = isLoading,
        onDownload = { url ->
            // Скачиваем изображение через корутину
            coroutineScope.launch {
                try {
                    downloadImage(context, url)
                } catch (e: Exception) {
                    // Обрабатываем ошибки загрузки
                    e.printStackTrace()
                }
            }
        }
    )
}
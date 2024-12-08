package com.iu6_mamykin.wonderpicturesclient

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
    var showPictureViewer by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope() // Для запуска корутин

    WonderPicturesScreen(
        themes = listOf("nature", "space", "animals", "landscapes", "cities"),
        selectedTheme = selectedTheme,
        onThemeSelect = { theme ->
            selectedTheme = theme
            isLoading = true
            showPictureViewer = true
            Log.d("WonderPicturesApp", "Requesting picture for theme: $theme")
            /// Запускаем корутину через rememberCoroutineScope
            coroutineScope.launch {
                try {
                    /*Log.d("WonderPicturesApp", "We in try")*/
                    val response = api.getPicture(theme)
                    /* Log.d("WonderPicturesApp", "response is final")*/
                    /*Log.d("WonderPicturesApp", "response != null")
                    Log.d("WonderPicturesApp", "Received picture URL: $response")*/
                    pictureUrl = response.url
                } catch (e: Exception) {
                    // Обрабатываем ошибки запроса
                   /* Log.e("WonderPicturesApp", "Error in API call: ${e.message}")*/
                    e.printStackTrace()
                    pictureUrl = null
                } finally {
                    isLoading = false
                }
            }
        },
        pictureUrl = if (showPictureViewer) pictureUrl else null,
        isLoading = isLoading,
        onCloseViewer = { showPictureViewer = false },
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
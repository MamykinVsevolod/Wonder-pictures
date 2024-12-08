package com.iu6_mamykin.wonderpicturesclient

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter


@Composable
fun PictureViewer(pictureUrl: String?, isLoading: Boolean, onCloseViewer: () -> Unit) {
    var loadingState by remember { mutableStateOf(true) }

    // Создаем painter для управления состоянием
    val painter = rememberAsyncImagePainter(
        model = pictureUrl,
        onState = {
            when (it) {
                is AsyncImagePainter.State.Loading -> {
                    loadingState = true
                }
                is AsyncImagePainter.State.Success -> {
                    loadingState = false
                }
                is AsyncImagePainter.State.Error -> {
                    loadingState = false
                }
                else -> Unit
            }
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.7f))
    ) {
        // Кнопка для закрытия просмотра
        IconButton(
            onClick = onCloseViewer,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close Viewer",
                tint = Color.White
            )
        }

        // Отображаем индикатор загрузки или изображение
        if (loadingState || isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        }

        // Изображение отрисовывается всегда, но состояние контролируется через `loadingState`
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 70.dp, end = 20.dp, bottom = 100.dp)
                .align(Alignment.Center)
        )
    }
}

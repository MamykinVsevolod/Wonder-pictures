package com.iu6_mamykin.wonderpicturesclient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WonderPicturesScreen(
    themes: List<String>,
    selectedTheme: String?,
    onThemeSelect: (String) -> Unit,
    pictureUrl: String?,
    isLoading: Boolean,
    onCloseViewer: () -> Unit,
    onDownload: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Основной экран с выпадающим списком
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var textFieldValue by remember { mutableStateOf(selectedTheme ?: "") }
            var expanded by remember { mutableStateOf(false) }

            Text(
                text = "Выберите тему",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = textFieldValue,
                    onValueChange = { textFieldValue = it },
                    readOnly = true,
                    label = { Text("Тема") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor() // Связывает меню с текстовым полем
                        .fillMaxWidth()
                )

                // Меню, связанное с текстовым полем
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                ) {
                    themes.forEach { theme ->
                        DropdownMenuItem(
                            text = { Text(theme) },
                            onClick = {
                                expanded = false
                                onThemeSelect(theme)
                                textFieldValue = theme
                            }
                        )
                    }
                }
            }
        }

        // Затемнение и изображение, если URL присутствует
        if (pictureUrl != null) {
            PictureViewer(
                pictureUrl = pictureUrl,
                isLoading = isLoading,
                onCloseViewer = onCloseViewer, // Передаем обработчик в PictureViewer
            )

            // Кнопка для скачивания под изображением
            if (!isLoading) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    DownloadButton(onClick = { onDownload(pictureUrl) })
                }
            }
        }
    }
}
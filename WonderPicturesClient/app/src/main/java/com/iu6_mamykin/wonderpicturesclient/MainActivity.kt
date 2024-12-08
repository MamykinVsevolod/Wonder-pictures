package com.iu6_mamykin.wonderpicturesclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.iu6_mamykin.wonderpicturesclient.ui.theme.WonderPicturesClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WonderPicturesClientTheme {
                WonderPicturesApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WonderPicturesClientTheme {
        WonderPicturesApp()
    }
}
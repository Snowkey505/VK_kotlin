package com.example.vk_hw_02

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest

class GifActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gifUrl = intent?.getStringExtra(GIF_URL) ?: ""

        setContent {
            GifISingleView(gifUrl)
        }
    }

    companion object{
        const val GIF_URL = "gif_url"

        fun start(context: Context, gifUrl: String){
            val intent = Intent(context, GifActivity::class.java).apply {
                putExtra(GIF_URL, gifUrl)
            }
            context.startActivity(intent)
        }
    }
}

@Composable
fun GifISingleView(gifUrl: String){
    val localContext = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(localContext).memoryCacheKey(gifUrl).diskCacheKey(gifUrl)
            //lruCache
            .data(gifUrl)
            .crossfade(true)
            .decoderFactory(GifDecoder.Factory())
            .build(),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp),
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.error_placeholder)
    )
}

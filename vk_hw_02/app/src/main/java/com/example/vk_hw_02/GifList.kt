package com.example.vk_hw_02

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.request.ImageRequest

@Composable
fun GifList(gifs: List<GifData>, onLoadMore: () -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(gifs) { gif ->
            GifItem(gif)
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
                onLoadMore()
            }
        }
    }
}

@Composable
fun GifItem(gif: GifData) {
    val context = LocalContext.current

    val imageUrl = gif.images.original.url

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(8.dp)
            .clickable {
                GifActivity.start(context, imageUrl)
            },
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context).memoryCacheKey(imageUrl)
                .data(imageUrl)
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
}
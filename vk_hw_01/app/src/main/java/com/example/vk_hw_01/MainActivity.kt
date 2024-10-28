package com.example.vk_hw_01

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vk_hw_01.ui.theme.Vk_hw_01Theme

class MainActivity : ComponentActivity() {
    private var size: Int = 0
    private lateinit var data: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            size = savedInstanceState?.getInt("size") ?: 0
            data = MutableList(size) { "${it + 1}" }
            data = blocks(data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("size", data.size)
    }
}

@Composable
fun blocks(initialData: List<String>): MutableList<String> {
    val isPortrait = LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT
    val columns = if (isPortrait) 3 else 4
    val data = remember { mutableStateListOf(*initialData.toTypedArray()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.weight(1f)
        ) {
            itemsIndexed(data) { index, item ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .aspectRatio(1f)
                        .background(if (index % 2 == 0) Color.Red else Color.Blue)
                ) {
                    Text(
                        text = item,
                        fontSize = 30.sp,
                        color = Color.White)
                }
            }
        }


        Button (
            onClick = { data.add("${data.size + 1}") },
            colors = ButtonDefaults.textButtonColors(contentColor = Color.Black,
                containerColor = Color.White),
            border = BorderStroke(width = 2.dp, color = Color.Black)
        ) {
            Text(
                text = "+",
                fontSize = 20.sp
            )
        }
    }
    return data
}

package com.lukaszstepien.colormixer19_09_24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukaszstepien.colormixer19_09_24.ui.theme.ColorMixer19_09_24Theme
import com.lukaszstepien.colormixer19_09_24.ui.theme.Pink40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorMixer19_09_24Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var redValue by remember { mutableIntStateOf(0) }
                    var greenValue by remember { mutableIntStateOf(0) }
                    var blueValue by remember { mutableIntStateOf(0) }

                    Box(modifier = Modifier.padding(innerPadding)) {
                        ColorBox(colorMix = Color(redValue, greenValue, blueValue).toArgb())

                        Row(Modifier.padding(20.dp, 210.dp, 20.dp, 0.dp)) {
                            Column(
                                verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.height(200.dp).padding(0.dp, 0.dp, 100.dp, 0.dp)
                            ) {
                                RedSlider(
                                    value = redValue,
                                    onValueChange = { redValue = it})

                                GreenSlider(
                                    value = greenValue,
                                    onValueChange = {greenValue = it}
                                )

                                BlueSlider(
                                    value = blueValue,
                                    onValueChange = {blueValue = it}
                                )
                            }
                        }

                        Row(Modifier.padding(0.dp, 220.dp, 50.dp, 50.dp)) {
                            Column(
                                modifier = Modifier.fillMaxWidth().height(180.dp),
                                horizontalAlignment =  Alignment.End,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                RedValue(value = redValue)
                                GreenValue(value = greenValue)
                                BlueValue(value = blueValue)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ColorBox(colorMix: Int, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(colorMix))
            .height(200.dp)
    )
}

@Composable
fun RedSlider(value: Int, onValueChange: (Int) -> Unit) {
    Slider(
        value = value.toFloat(),
        onValueChange = { onValueChange(it.toInt())},
        valueRange = 0f..255f,
    )
}

@Composable
fun RedValue(value: Int) {
    Text(
        text = "R: $value",
        color = Color(0xFFFF0000)
    )
}

@Composable
fun GreenSlider(value: Int, onValueChange: (Int) -> Unit) {
    Slider(
        value = value.toFloat(),
        onValueChange = { onValueChange(it.toInt())},
        valueRange = 0f..255f,
    )
}

@Composable
fun GreenValue(value: Int) {
    Text(
        text = "G: $value",
        color = Color(0xFF00FF00)
    )
}

@Composable
fun BlueSlider(value: Int, onValueChange: (Int) -> Unit) {
    Slider(
        value = value.toFloat(),
        onValueChange = { onValueChange(it.toInt())},
        valueRange = 0f..255f,
    )
}

@Composable
fun BlueValue(value: Int) {
    Text(
        text = "B: $value",
        color = Color(0xFF0000FF)
    )
}
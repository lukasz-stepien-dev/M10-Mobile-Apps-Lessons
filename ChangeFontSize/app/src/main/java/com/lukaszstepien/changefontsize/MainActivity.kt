package com.lukaszstepien.changefontsize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukaszstepien.changefontsize.ui.theme.ChangeFontSizeTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChangeFontSizeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding).padding(20.dp)) {
                        var fontSize by remember { mutableIntStateOf(30) } // Domyślny rozmiar tekstu
                        val greetings = arrayOf("Dzień dobry", "Good morning", "Buenos dias")
                        var indexGreeting by remember { mutableIntStateOf(0) }

                        HeaderTitle()
                        FontSize(fontSize)
                        SliderFontSize { fontSize = it.toInt() }
                        Greeting(greetings[indexGreeting], fontSize.sp)
                        ChangeGreetingBtn {
                            indexGreeting = (indexGreeting + 1) % greetings.size
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderTitle() {
    Text(
        text = "Właściwości czcionki",
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.background(Color(0xFF558B2F)).fillMaxWidth(),
        color = Color.White,
    )
}

@Composable
fun FontSize(size: Int) {
    Text(
        text = "Rozmiar: $size",
    )
}

@Composable
fun SliderFontSize(onValueChange: (Float) -> Unit) {
    var fontSize by remember { mutableFloatStateOf(30f) }
    Slider(
        valueRange = 0f..40f,
        value = fontSize,
        onValueChange = {
            fontSize = it
            onValueChange(it)
        }
    )
}

@Composable
fun Greeting(greeting: String, size: TextUnit ) {
    Text(
        text = greeting,
        fontSize = size
    )
}

@Composable
fun ChangeGreetingBtn(onClick: () -> Unit) {

    Button(
        onClick = { onClick() },
        modifier = Modifier.fillMaxWidth(),
    ) { Text(">>") }
}

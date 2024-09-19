 package com.example.simplecalculator18_09_24

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simplecalculator18_09_24.ui.theme.DarkRed
import com.example.simplecalculator18_09_24.ui.theme.LightGreen
import com.example.simplecalculator18_09_24.ui.theme.Red
import com.example.simplecalculator18_09_24.ui.theme.SimpleCalculator18_09_24Theme
import com.example.simplecalculator18_09_24.ui.theme.White

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimpleCalculator18_09_24Theme {
                var a by remember { mutableIntStateOf(0) }
                var b by remember { mutableIntStateOf(0) }
                var c by remember { mutableIntStateOf(0) }
                var sum by remember { mutableFloatStateOf(0f) }
                var percentage by remember { mutableFloatStateOf(0f) }
                var checked by remember { mutableIntStateOf(0) }
                var oldChecked by remember { mutableIntStateOf(0) }

                Box(modifier = Modifier.fillMaxSize()
                    .background(LightGreen))
                HeaderCalc()
                FactorA() { a = it.toInt() }
                FactorB() { b = it.toInt() }
                FactorC() { c = it.toInt() }
                CheckboxPercentage() { checked = if (it) 1 else 0 }
                BtnCalc {
                    sum = (a + b + c).toFloat()
                    percentage = sum * 0.15f
                    oldChecked = checked
                }
                CalcResult(sum = sum.toInt(), factorC = c, percentage = percentage, checked = oldChecked == 1)
            }
        }
    }
}

 @Composable
 fun HeaderCalc(modifier: Modifier = Modifier) {
     Text(
         text = "Kalkulator Prosty",
         color = White,
         fontSize = 30.sp,
         textAlign = TextAlign.Center,
         modifier = Modifier
             .padding(0.dp, 50.dp, 0.dp, 0.dp)
             .width(1000.dp)
             .background(DarkRed)
             .padding(10.dp)
     )

 }

 @Composable
 fun FactorA(onValueChange: (String) -> Unit) {
     var number by remember { mutableStateOf("") }
     TextField(
         modifier = Modifier
             .padding(10.dp, 120.dp, 10.dp, 10.dp)
             .fillMaxWidth(),
         maxLines = 1,
         value = number,
         onValueChange = { number = it
                         onValueChange(it) },
         keyboardOptions = KeyboardOptions(
             keyboardType = KeyboardType.Number
         ),
         label = { Text("Podaj a: ") },
     )

 }

 @Composable
 fun FactorB(onValueChange: (String) -> Unit) {
     var number by remember { mutableStateOf("") }
     TextField(
         modifier = Modifier
             .padding(10.dp, 190.dp, 10.dp, 10.dp)
             .fillMaxWidth(),
         value = number,
         maxLines = 1,
         onValueChange = { number = it
                         onValueChange(it) },
         keyboardOptions = KeyboardOptions(
             keyboardType = KeyboardType.Number
         ),
         label = { Text("Podaj b: ") },
     )

 }

 @Composable
 fun FactorC(onValueChange: (Float) -> Unit) {
     var sliderPosition by remember { mutableFloatStateOf(0f) }
     Row(modifier = Modifier.padding(10.dp, 250.dp, 10.dp, 10.dp)) {
         Text("Podaj c: ", modifier = Modifier.align(Alignment.CenterVertically))
         Slider(
             value = sliderPosition,
             onValueChange = { sliderPosition = it
                             onValueChange(it) },
                valueRange = 1f..10f,
         )
     }
 }

 @Composable
 fun CheckboxPercentage(onValueChange: (Boolean) -> Unit) {
     var checked by remember { mutableStateOf(true) }
     Row(modifier = Modifier.padding(10.dp, 310.dp, 10.dp, 10.dp).fillMaxWidth(),
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it
                onValueChange(it)}
            )
         Text(
                text = "15% wartości",
         )
     }

 }

 @Composable
 fun BtnCalc(modifier: Modifier = Modifier, onClick: () -> Unit) {
     Row(modifier = Modifier.padding(10.dp, 380.dp, 10.dp, 10.dp).fillMaxWidth(),
         horizontalArrangement = Arrangement.Center,) {
         Button( onClick = {onClick()},
             colors = ButtonDefaults.buttonColors(containerColor = Red)
         ) {
                Text("Oblicz")
         }

     }
 }

@Composable
 fun CalcResult(modifier: Modifier = Modifier, sum: Int = 0, factorC: Int = 0, percentage: Float = 0f, checked: Boolean = true) {
     Column(modifier = Modifier.padding(0.dp, 450.dp, 0.dp, 0.dp).fillMaxWidth()
                            .height(100.dp)
                            .background(DarkRed),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center) {
         Text(
             text = "Suma liczb wynosi: $sum",
             color = White
         )
         Text(
             text = "c wynosi: $factorC",
             color = White
         )

        if (checked) {
            Text(
                text = "15% wartości wynosi: $percentage",
                color = White
            )
        } else {
            Text(
                text = "",
                color = White
            )
        }

     }
 }
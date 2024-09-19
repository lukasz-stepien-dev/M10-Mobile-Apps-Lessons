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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
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
                Box(modifier = Modifier.fillMaxSize()
                    .background(LightGreen))
                HeaderCalc()
                FactorA()
                FactorB()
                FactorC()
                CheckboxPercentage()
                BtnCalc {  }
                CalcResult()
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
 fun FactorA(modifier: Modifier = Modifier) {
     var number by remember { mutableStateOf("") }
     TextField(
         modifier = Modifier
             .padding(10.dp, 120.dp, 10.dp, 10.dp)
             .fillMaxWidth(),
         value = number,
         onValueChange = { number = it },
         keyboardOptions = KeyboardOptions(
             keyboardType = KeyboardType.Number
         ),
         label = { Text("Podaj a: ") },
     )

 }

 @Composable
 fun FactorB(modifier: Modifier = Modifier) {
     var number by remember { mutableStateOf("") }
     TextField(
         modifier = Modifier
             .padding(10.dp, 190.dp, 10.dp, 10.dp)
             .fillMaxWidth(),
         value = number,
         onValueChange = { number = it },
         keyboardOptions = KeyboardOptions(
             keyboardType = KeyboardType.Number
         ),
         label = { Text("Podaj b: ") },
     )

 }

 @Composable
 fun FactorC(modifier: Modifier = Modifier) {
     var sliderPosition by remember { mutableFloatStateOf(0f) }
     Row(modifier = Modifier.padding(10.dp, 250.dp, 10.dp, 10.dp)) {
         Text("Podaj c: ", modifier = Modifier.align(Alignment.CenterVertically))
         Slider(
             value = sliderPosition,
             onValueChange = { sliderPosition = it }
         )
     }
 }

 @Composable
 fun CheckboxPercentage(modifier: Modifier = Modifier) {
     var checked by remember { mutableIntStateOf(0) }
     Row(modifier = Modifier.padding(10.dp, 310.dp, 10.dp, 10.dp).fillMaxWidth(),
         horizontalArrangement = Arrangement.Center,
         verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checked == 1,
                onCheckedChange = { checked = if (it) 1 else 0 }
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
 fun CalcResult(modifier: Modifier = Modifier, sum: Float = 0f, factorC: Int = 0, percentage: Float = 0f) {
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
         Text(
             text = "15% wartości wynosi $percentage",
                color = White
         )
     }
 }
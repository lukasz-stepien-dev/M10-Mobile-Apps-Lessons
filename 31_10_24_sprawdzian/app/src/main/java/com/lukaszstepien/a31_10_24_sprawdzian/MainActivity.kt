package com.lukaszstepien.a31_10_24_sprawdzian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukaszstepien.a31_10_24_sprawdzian.ui.theme._31_10_24_sprawdzianTheme
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _31_10_24_sprawdzianTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        HeaderTitle()
                        TriangleCalculator()
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.Green)
            .padding(50.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier,
            text = "Kartkówka nr 1 - Łukasz Stępień",
        )
    }
}

@Composable
fun TriangleCalculator() {
    var valueA by remember { mutableStateOf(1) }
    var valueB by remember { mutableStateOf(1) }
    var valueC by remember { mutableStateOf(1) }
    var isRightTriangle by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("") }
    var areaResult by remember { mutableStateOf("") }
    val history = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.padding(16.dp)) {
        TriangleInputs(
            valueA = valueA.toFloat(),
            valueB = valueB.toFloat(),
            valueC = valueC.toFloat(),
            onValueChangeA = { valueA = it.toInt() },
            onValueChangeB = { valueB = it.toInt() },
            onValueChangeC = { valueC = it.toInt() }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isRightTriangle,
                onCheckedChange = { isRightTriangle = it }
            )
            Text("Czy to trójkąt prostokątny?")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    val a = valueA
                    val b = valueB
                    val c = valueC
                    if (a + b > c && a + c > b && b + c > a) {
                        val perimeter = a + b + c
                        result = "a = $a, b = $b, c = $c, Obwód wynosi: $perimeter"
                        if (isRightTriangle) {
                            val sides = listOf(a, b, c).sorted()
                            if (sides[0].toDouble().pow(2) + sides[1].toDouble().pow(2) == sides[2].toDouble().pow(2)) {
                                val area = 0.5 * sides[0] * sides[1]
                                areaResult = "Pole wynosi: $area"
                            } else {
                                areaResult = "Nie jest to trójkąt prostokątny"
                            }
                        } else {
                            areaResult = ""
                        }
                    } else {
                        result = "a = $a, b = $b, c = $c, Nie tworzy trójkąta"
                        areaResult = ""
                    }
                    history.add("$result $areaResult")
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("Sprawdź i oblicz")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(result)
        if (areaResult.isNotEmpty()) {
            Text(areaResult)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Historia:")
        LazyColumn {
            items(history) { item ->
                Text(item)
            }
        }
    }
}

@Composable
fun TriangleInputs(
    valueA: Float,
    valueB: Float,
    valueC: Float,
    onValueChangeA: (Float) -> Unit,
    onValueChangeB: (Float) -> Unit,
    onValueChangeC: (Float) -> Unit
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Podaj a:")
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = valueA,
                onValueChange = onValueChangeA,
                valueRange = 1f..20f,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = valueA.toString(),
                onValueChange = {},
                enabled = false,
                modifier = Modifier.width(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Podaj b:")
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = valueB,
                onValueChange = onValueChangeB,
                valueRange = 1f..20f,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = valueB.toString(),
                onValueChange = {},
                enabled = false,
                modifier = Modifier.width(60.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Podaj c:")
            Spacer(modifier = Modifier.width(8.dp))
            Slider(
                value = valueC,
                onValueChange = onValueChangeC,
                valueRange = 1f..20f,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            TextField(
                value = valueC.toString(),
                onValueChange = {},
                enabled = false,
                modifier = Modifier.width(60.dp)
            )
        }
    }
}
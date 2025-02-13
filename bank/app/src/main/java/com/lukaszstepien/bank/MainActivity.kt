package com.lukaszstepien.bank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukaszstepien.bank.ui.theme.BankTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BankTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var pesel by remember { mutableStateOf("") }
                    var isPeselCorrect by remember { mutableStateOf(false) }
                    var amount by remember { mutableDoubleStateOf(0.0) }
                    var percent by remember { mutableDoubleStateOf(0.0) }
                    var months by remember { mutableIntStateOf(0) }
                    var result by remember { mutableDoubleStateOf(0.0) }
                    var errorMessage by remember { mutableStateOf("") }
                    var banknoteRepresentation by remember { mutableStateOf("") }
                    Box(modifier = Modifier.padding(innerPadding).fillMaxWidth()) {
                        Text(text = "Wpisz swój PESEL")
                        TextField(
                            value = pesel,
                            onValueChange = { pesel = it },
                            Modifier.padding(top = 20.dp)
                        )
                        Button(
                            onClick = {
                                if (peselCheck(pesel)) {
                                    isPeselCorrect = true
                                } else {
                                    isPeselCorrect = false
                                    errorMessage = "Niepoprawny PESEL"
                                }
                            },
                            modifier = Modifier.padding(top = 80.dp)
                        ) { Text("Sprawdź PESEL") }
                        if (isPeselCorrect) {
                            Text(text = "Wpisz kwotę, oprocentowanie i ilość miesięcy", Modifier.padding(top = 100.dp))
                            TextField(
                                value = amount.toString(),
                                onValueChange = { amount = it.toDouble() },
                                Modifier.padding(top = 200.dp)
                            )
                            TextField(
                                value = percent.toString(),
                                onValueChange = { percent = it.toDouble() },
                                Modifier.padding(top = 300.dp)
                            )
                            TextField(
                                value = months.toString(),
                                onValueChange = { months = it.toInt() },
                                Modifier.padding(top = 400.dp)
                            )
                            Button(
                                onClick = {
                                    result = calculate(amount, percent, months)
                                    if (result.isNaN() || result.isInfinite() || result <= 0.0) {
                                        errorMessage = "Błąd obliczeń"
                                    } else {
                                        banknoteRepresentation = representAsBanknotes(result.toInt())
                                    }
                                },
                                modifier = Modifier.padding(top = 500.dp)
                            ) { Text("Oblicz ratę") }
                        }
                        if (result > 0.0) {
                            Text(text = "Rata wynosi: ${Math.floor(result * 100) / 100}", Modifier.padding(top = 600.dp))
                            Text(text = "Banknoty: $banknoteRepresentation", Modifier.padding(top = 650.dp))
                        }

                        if (errorMessage.isNotEmpty()) {
                            Text(text = errorMessage, Modifier.padding(top = 700.dp))
                        }
                    }
                }
            }
        }
    }
}

fun peselCheck(pesel: String): Boolean {
    if (pesel.length != 11) return false

    val weights = listOf(1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
    val sum = pesel.take(10).mapIndexed { index, char ->
        Character.getNumericValue(char) * weights[index]
    }.sumOf { it % 10 }

    val controlDigit = (10 - (sum % 10)) % 10
    return controlDigit == Character.getNumericValue(pesel.last())
}

fun calculate(amount: Double, percent: Double, months: Int): Double {
    return amount * (percent / 100 / 12) * (1 + percent / 100 / 12).pow(months) / ((1 + percent / 100 / 12).pow(months) - 1)
}

fun representAsBanknotes(amount: Int): String {
    if (amount == 0) return "0"

    val banknotes = listOf(500, 200, 100, 50, 20, 10, 5, 2, 1)
    val banknoteCounts = mutableMapOf<Int, Int>()

    var remainingAmount = amount
    for (banknote in banknotes) {
        if (remainingAmount >= banknote) {
            val count = remainingAmount / banknote
            banknoteCounts[banknote] = count
            remainingAmount %= banknote
        }
    }

    val outputParts = banknoteCounts.map { (banknote, count) ->
        if (count > 1) "$count x $banknote" else "$banknote"
    }

    return if (outputParts.size == 1) {
        outputParts[0]
    } else {
        outputParts.dropLast(1).joinToString(", ") + " and " + outputParts.last()
    }
}
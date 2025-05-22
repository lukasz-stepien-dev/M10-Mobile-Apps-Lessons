package com.lukaszstepien.practiseinf04

import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderState
import androidx.compose.material3.Switch
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
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukaszstepien.practiseinf04.ui.theme.Practiseinf04Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practiseinf04Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        var text by remember { mutableStateOf("") }
                        var isChecked by remember { mutableStateOf(false) }
                        val options = listOf("Kebab", "Paulinka fryteczki", "Kawusia :D")
                        var selectedOption by remember { mutableStateOf(options[0]) }
                        val pictures = listOf(R.drawable.kebab, R.drawable.fryteczki, R.drawable.kawusia)
                        var selectedPicture by remember { mutableStateOf(pictures[0]) }
                        Column (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            TextField(
                                value = text,
                                onValueChange = {text = it},
                                label = { Text("Wprowadź tekst") },
                                visualTransformation = PasswordVisualTransformation(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            )
                            Row{
                                Button(
                                    onClick = {},
                                ) {
                                    Text("Click me!")
                                }
                                Button(
                                    onClick = {
                                        Toast.makeText(this@MainActivity, "Something", Toast.LENGTH_SHORT).show()
                                    }
                                ) {
                                    Text("Click me")
                                }
                            }

                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = isChecked,
                                    onCheckedChange = {isChecked = it},
                                )
                                Text("Sample checkbox with text ")
                            }

                            options.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = (option == selectedOption),
                                        onClick = {
                                            selectedOption = option
                                            if (option == options[0])
                                                selectedPicture = pictures[0]
                                            else if (option == options[1])
                                                selectedPicture = pictures[1]
                                            else
                                                selectedPicture = pictures[2]


                                        }

                                    )
                                    Text(option)
                                }
                            }

                            var sliderValue by remember { mutableIntStateOf(0) }
                            Column {
                                Slider(
                                    value = sliderValue.toFloat(),
                                    onValueChange = {sliderValue = it.toInt()},
                                    valueRange = 0.0F..20.0F,
                                )

                                Text(sliderValue.toString())
                            }

                            var isToggled by remember { mutableStateOf(false) }
                            Switch(
                                checked = isToggled,
                                onCheckedChange = {isToggled = it}
                            )

                            Image(
                                painter = painterResource(id = selectedPicture),
                                contentDescription = ""
                            )



                        }

                    }
                }
            }
        }
    }

}

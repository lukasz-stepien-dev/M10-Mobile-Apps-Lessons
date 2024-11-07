package com.lukaszstepien.a31_10_24_vet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukaszstepien.a31_10_24_vet.ui.theme._31_10_24_VetTheme
import java.sql.Time

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _31_10_24_VetTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var selectedAnimal by remember { mutableStateOf("") }
                    var ownerData by remember { mutableStateOf("") }
                    var animalAge by remember { mutableIntStateOf(0) }
                    var maxAnimalAge by remember { mutableFloatStateOf(0f) }
                    var purpose by remember { mutableStateOf("") }
                    val timeState = rememberTimePickerState(16, 0, true);

                    // Set maxAnimalAge based on selectedAnimal
                    when (selectedAnimal) {
                        "Pies" -> maxAnimalAge = 18f
                        "Kot" -> maxAnimalAge = 20f
                        "Świnska Morska" -> maxAnimalAge = 9f
                    }

                    Header(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                    Box(
                        modifier = Modifier.padding(innerPadding).padding(0.dp, 55.dp, 0.dp, 0.dp).background(Color(0xFF90EE90)).fillMaxSize()
                    ) {
                        OwnerData(ownerData, onOwnerDataChanged = { ownerData = it })
                        AnimalsList(selectedAnimal, onAnimalSelected = {
                            selectedAnimal = it
                            animalAge = 0
                        })
                        AnimalAge(animalAge, selectedAnimal, maxAnimalAge, onAnimalAgeChanged = {
                            animalAge = it
                        })
                        Purpose(purpose, timeState, onPurposeChanged = {purpose = it})
                    }
                }
            }
        }
    }
}

@Composable
fun Header(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Wizyta u weterynarza",
        modifier = modifier.fillMaxWidth().background(Color(0xFF2E8B57)).padding(10.dp),
        fontSize = 32.sp,
    )
}

@Composable
fun OwnerData(ownerData: String, onOwnerDataChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = "",
        onValueChange = {onOwnerDataChanged(it)},
        label = { Text("Imię i nazwisko właściciela") },
    )
}

@Composable
fun AnimalsList(selectedAnimal: String, onAnimalSelected: (String) -> Unit) {
    Text(text = "Gatunek", modifier = Modifier.padding(0.dp,70.dp,0.dp,0.dp))
    val animals = listOf("Pies", "Kot", "Świnska Morska");
    LazyColumn(modifier = Modifier.padding(0.dp,100.dp,0.dp,0.dp)) {
        items(animals) { animal ->
            val isSelected = animal == selectedAnimal
            Text(
                text = animal,
                modifier = Modifier

                    .background(if (isSelected) Color.Magenta else Color.Transparent)
                    .fillMaxWidth()
                    .padding(0.dp, 10.dp)
                    .clickable { onAnimalSelected(animal) }
                )

        }
    }
}

@Composable
fun AnimalAge(animalAge: Int, animalSelected: String, maxAnimalAge: Float, onAnimalAgeChanged: (Int) -> Unit) {

    Row(Modifier.padding(0.dp, 250.dp, 0.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Ile ma lat? $animalAge")
        Slider(
            value = animalAge.toFloat(),
            onValueChange = {onAnimalAgeChanged(it.toInt())},
            valueRange = 0f..maxAnimalAge,
            modifier = Modifier.padding(10.dp)
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Purpose(purpose: String, timeState: TimePickerState, onPurposeChanged: (String) -> Unit) {
    TextField(
        modifier = Modifier.padding(0.dp, 340.dp, 0.dp, 0.dp).fillMaxWidth(),
        value = purpose,
        onValueChange = { onPurposeChanged(it) },
        label = { Text("Cel wizyty") },
    )
    TimePicker(
        modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp),
        state = timeState,

    )
}

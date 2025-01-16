package com.lukaszstepien.practiseforshorttest160125

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lukaszstepien.practiseforshorttest160125.ui.theme.PractiseForShortTest160125Theme
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PractiseForShortTest160125Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var showTimePicker by remember { mutableStateOf(false) }
                    var hour by remember { mutableIntStateOf(0) }
                    var minute by remember { mutableIntStateOf(0) }
                    val times = remember { mutableListOf<String>() }
                    Box(modifier = Modifier.padding(innerPadding)) {
                        TimerPicker(
                            showTimePicker = showTimePicker,
                            onConfirm = { h, m ->
                                hour = h
                                minute = m
                                showTimePicker = false
                                times.add("${if (h < 10) "0$h" else "$h"}:${if (m < 10) "0$m" else "$m"}")
                            },
                            onInputTimeClicked = {
                                showTimePicker = true
                            },
                            h = hour,
                            m = minute
                        )
                        ListOfTimes(times)
                    }
                }
            }
        }
    }
}

@Composable
fun TimerPicker(
    showTimePicker: Boolean,
    onConfirm: (hour: Int, minute: Int) -> Unit,
    onInputTimeClicked: () -> Unit = {},
    h: Int = 0,
    m: Int = 0,
) {
    if (showTimePicker) {
        TimerPickerDialog { hour: Int, minute: Int ->
            onConfirm(hour, minute)
        }
    } else {
        TextField(
            value = "${if (h < 10) "0$h" else "$h"}:${if (m < 10) "0$m" else "$m"}",
            onValueChange = {},
            modifier = Modifier.clickable { onInputTimeClicked() },
            enabled = false
        )
    }
}

@Composable
fun TimerPickerDialog(
    onConfirm: (hour: Int, minute: Int) -> Unit,
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(
        context,
        3,
        { _, selectedHour, selectedMinute ->
            onConfirm(selectedHour, selectedMinute)
        },
        hour,
        minute,
        true
    ).show()
}

@Composable
fun ListOfTimes(list: List<String>) {
    LazyColumn {
        items(list) { time ->
            Text(text = time)
        }
    }
}
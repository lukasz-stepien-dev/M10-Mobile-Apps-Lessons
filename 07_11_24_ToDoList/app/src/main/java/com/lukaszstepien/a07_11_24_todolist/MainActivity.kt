package com.lukaszstepien.a07_11_24_todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lukaszstepien.a07_11_24_todolist.ui.theme._07_11_24_ToDoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _07_11_24_ToDoListTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var tasks by remember { mutableStateOf(listOf("Zakupy: Chleb, masło, ser", "Do zrobienia: obiad, umyć podłogi", "weekend: kino, spacer z psem")) }
                    var taskToAdd by remember { mutableStateOf("") }
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        TaskInput(
                            taskToAdd,
                            onValueChanged = { taskToAdd = it },
                            onAddClicked = { tasks += taskToAdd },
                        )
                        TaskList(tasks)
                    }

                }
            }
        }
    }
}

@Composable
fun TaskInput(text: String, onValueChanged: (String) -> Unit, onAddClicked: () -> Unit) {
    Row {
        TextField(
            value = text,
            onValueChange = { onValueChanged(it) },
            label = { Text("Nowy Element") }
        )
        Button(
            onClick = { onAddClicked() },
            modifier = Modifier.padding(top = 8.dp),
            colors = ButtonColors(
    containerColor = Color(0xFFDC143C),
    contentColor = Color.White,
    disabledContainerColor = Color.Gray,
    disabledContentColor = Color.LightGray
)
        ) {
            Text("DODAJ")
        }
    }
}

@Composable
fun TaskList(tasks: List<String>) {
    LazyColumn(modifier = Modifier.padding(top = 100.dp).fillMaxWidth()) {
        items(tasks.size) {index: Int ->
            Text(text = tasks[index], color = Color(0xFFDC143C), modifier = Modifier.padding(top = 5.dp, bottom = 5.dp))
            HorizontalDivider(color = Color(0xFFDC143C), thickness = 2.dp, modifier = Modifier.padding(vertical = 5.dp))
        }
    }
}
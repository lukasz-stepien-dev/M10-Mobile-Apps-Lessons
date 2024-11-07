package com.lukaszstepien.a07_11_24_email

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukaszstepien.a07_11_24_email.ui.theme._07_11_24_EmailTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _07_11_24_EmailTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var email by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var repeatedPassword by remember { mutableStateOf("") }
                    var info by remember { mutableStateOf("Autor: Łukasz Stępień") }
                    Header()
                    Form(email, password, repeatedPassword, onEmailChanged = {email = it}, onPasswordChanged = {password = it}, onRepeatedPasswordChanged = {repeatedPassword = it})
                    AcceptButton (onAcceptClicked = {
                        info = if (email.contains('@')) {
                            if (password == repeatedPassword) {
                                "Witaj $email"
                            } else {
                                "Hasła nie są takie same"
                            }
                        } else {
                            "Email niepoprawny"
                        }
                    })
                    Info(info)
                }
            }
        }
    }
}

@Composable
fun Header() {
    Text(
        text = "Rejestruj konto",
        fontSize = 32.sp,
        color = Color.White,
        modifier = Modifier
            .background(Color(0xFF008080))
            .fillMaxWidth()
            .padding(top = 16.dp)

    )
}

@Composable
fun Form(email:String, password: String, repeatedPassword: String, onEmailChanged: (String) -> Unit, onPasswordChanged: (String) -> Unit, onRepeatedPasswordChanged: (String) -> Unit) {
    Text(
        text = "Podaj email:",
        color = Color.Gray,
        modifier = Modifier
            .padding(top = 60.dp)
    )
    TextField(
        value = email,
        onValueChange = { onEmailChanged(it) },
        modifier = Modifier
            .padding(top = 90.dp)
            .fillMaxWidth()
    )
    Text(
        text = "Podaj hasło:",
        color = Color.Gray,
        modifier = Modifier
            .padding(top = 150.dp)
    )
    TextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .padding(top = 180.dp)
            .fillMaxWidth()
    )
    Text(
        text = "Powtórz hasło:",
        color = Color.Gray,
        modifier = Modifier
            .padding(top = 250.dp)
    )
    TextField(
        value = repeatedPassword,
        onValueChange = { onRepeatedPasswordChanged(it) },
        visualTransformation = PasswordVisualTransformation(),
        modifier = Modifier
            .padding(top = 290.dp)
            .fillMaxWidth()
    )
}

@Composable
fun AcceptButton(onAcceptClicked: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = { onAcceptClicked() },
            modifier = Modifier.padding(top = 350.dp),
        ) {
            Text("Załóż konto")
        }
    }

}

@Composable
fun Info(info: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = info,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 400.dp)
        )
    }

}
package com.lukaszstepien.votingforyourcity

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lukaszstepien.votingforyourcity.ui.theme.Violet
import com.lukaszstepien.votingforyourcity.ui.theme.VotingForYourCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VotingForYourCityTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        var gda by remember { mutableIntStateOf(0) }
                        var ber by remember { mutableIntStateOf(0) }
                        var mad by remember { mutableIntStateOf(0) }
                        HeaderTitle()
                        Column(
                            modifier = Modifier
                                .padding(25.dp, 100.dp, 0.dp, 0.dp)
                                .height(600.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            DecreaseGda(onClick = {if (gda > 0) gda--})
                            DecreaseBer(onClick = {if (ber > 0) ber--})
                            DecreaseMad(onClick = {if (mad > 0) mad--})
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(0.dp, 100.dp, 0.dp, 0.dp).height(600.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceAround

                        ) {
                            Column() {
                                Image(painterResource(R.drawable.gdansk),"content description")
                                GdaLikes(gda)
                            }

                            Column {
                                Image(painterResource(R.drawable.berlin),"content description")
                                BerLikes(ber)
                            }

                            Column {
                                Image(painterResource(R.drawable.madrid),"content description")
                                MadLikes(mad)
                            }
                        }

                        Column(
                            modifier = Modifier
                                .padding(335.dp, 100.dp, 0.dp, 0.dp)
                                .height(600.dp),
                            verticalArrangement = Arrangement.SpaceAround
                        ) {
                            IncreaseGda(onClick = {gda++})
                            IncreaseBer(onClick = {ber++})
                            IncreaseMad(onClick = {mad++})
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun HeaderTitle () {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .background(Violet)
            .padding(30.dp),
        textAlign = TextAlign.Center,
        color = White,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        text = "Zagłosuj na swoje ulubione miasto",
    )
}

@Composable
fun DecreaseGda(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("-")
    }
}

@Composable
fun DecreaseBer(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("-")
    }
}

@Composable
fun DecreaseMad(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("-")
    }
}

@Composable
fun GdaLikes(likes: Int) {
    Text("$likes polubień", modifier = Modifier.padding(50.dp, 10.dp, 0.dp, 0.dp))
}

@Composable
fun BerLikes(likes: Int) {
    Text("$likes polubień", modifier = Modifier.padding(50.dp, 10.dp, 0.dp, 0.dp))
}

@Composable
fun MadLikes(likes: Int) {
    Text("$likes polubień", modifier = Modifier.padding(50.dp, 10.dp, 0.dp, 0.dp))
}

@Composable
fun IncreaseGda(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("+")
    }
}

@Composable
fun IncreaseBer(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("+")
    }
}

@Composable
fun IncreaseMad(onClick: () -> Unit) {
    Button(
        onClick = {onClick()},
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text("+")
    }
}
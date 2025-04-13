package com.example.captaingame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.captaingame.ui.theme.CaptainGameTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CaptainGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CaptainGame(paddingValues = innerPadding)
                }
            }
        }
    }
}

fun checkStromOrTreasure(stromOrTreasure: MutableState<String>, treasureFound: MutableState<Int>){
    if(Random.nextBoolean()){
        stromOrTreasure.value = "Treasure Found"
        treasureFound.value += 1
    }
    else stromOrTreasure.value = "Storm Ahead"
}

@Composable
fun CaptainGame(paddingValues: PaddingValues) {
    val stormOrTreasure = remember { mutableStateOf("") }
    val treasureFound = remember { mutableStateOf(0) }
    val direction = remember { mutableStateOf("North") }

    Column(modifier = Modifier.padding(paddingValues)) {
        Text(text = "Treasure Found: ${treasureFound.value}")
        Text(text = "Current Direction: ${direction.value}")


        Row {
            Button(
                onClick = {
                    direction.value = "North"
                    checkStromOrTreasure(stormOrTreasure, treasureFound)
                }
            ) {
                Text(text = "Sail North")
            }

            Button(
                onClick = {
                    direction.value = "South"
                    checkStromOrTreasure(stormOrTreasure, treasureFound)
                }
            ) {
                Text(text = "Sail South")
            }

            Button(
                onClick = {
                    direction.value = "East"
                    checkStromOrTreasure(stormOrTreasure, treasureFound)
                }
            ) {
                Text(text = "Sail East")
            }

            Button(
                onClick = {
                    direction.value = "West"
                    checkStromOrTreasure(stormOrTreasure, treasureFound)
                }
            ) {
                Text(text = "Sail West")
            }
        }

        Text(text = stormOrTreasure.value)
    }
}
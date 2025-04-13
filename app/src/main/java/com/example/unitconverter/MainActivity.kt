package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.foundation.layout.PaddingValues // ✅ import for PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UnitConverter(paddingValues = innerPadding)
                    }
                }
            }
        }
    }
}

@Composable
fun UnitConverter(paddingValues: PaddingValues) { // ✅ accept padding
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(22.dp))
        OutlinedTextField(value = "Enter Value Here", onValueChange = {

        })
        Spacer(modifier = Modifier.height(16.dp))

        Row() {
            Box {
                Button(onClick = {}) {
                    Text("Select")
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down"
                    )

                }

                DropdownMenu(expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {}
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {}
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {}
                    )
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Box {
                Button(onClick = {}) {
                    Text("Select")
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down"
                    )

                }

                DropdownMenu(expanded = false, onDismissRequest = {}) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {}
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {}
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {}
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("Result: ")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter(
        paddingValues = PaddingValues(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 8.dp
        )
    )
}

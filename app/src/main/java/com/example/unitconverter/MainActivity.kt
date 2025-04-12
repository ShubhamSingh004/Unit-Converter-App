package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.platform.LocalContext

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
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
           Text("Unit Converter")
        OutlinedTextField(value = "Enter Value Here", onValueChange = {

        })
        Row() {
            val context = LocalContext.current
            Button(onClick = {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
            }) {
                Text("Click Me")
            }
        }

        Text("Result: ")
    }
}


@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter(paddingValues = PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp))
}

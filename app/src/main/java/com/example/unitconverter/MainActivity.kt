package com.example.unitconverter

import android.os.Bundle
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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

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

val textStyle = TextStyle(
    fontFamily = FontFamily.Cursive,
    color = Color.Blue,
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun UnitConverter(paddingValues: PaddingValues) {
    var inputValue by remember { mutableStateOf("0.0") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputValue by remember { mutableStateOf("0.0") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) } // input expanded
    var oExpanded by remember { mutableStateOf(false) } // output expanded
    var conversionFactor= remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor= remember { mutableDoubleStateOf(1.00) }
    var result by remember { mutableDoubleStateOf(0.0) }


    fun convertUnit(){
        // ?: Elvis Operator
        val inputUnitDouble = inputValue.toDoubleOrNull() ?: 0.0
        result = (inputUnitDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt()/100.0
        outputValue = result.toString()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Unit Converter",
            style = textStyle
        )
        Spacer(modifier = Modifier.height(22.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                if(it.toIntOrNull() != null || it.toDoubleOrNull() != null){
                    inputValue = it
                    convertUnit()
                }
                else if(it == ""){
                    inputValue = it
                    outputValue = "0"
                }
            },
            label = {Text("Enter Input Value")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row{
            Box {
                // input dropdown button
                Button(
                    onClick = {
                        iExpanded = !iExpanded
                    }) {
                    Text(inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down"
                    )

                }

                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = {
                        iExpanded = false
                    }) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Box {
                // output dropdown button
                Button(
                    onClick = {
                        oExpanded = !oExpanded
                    }) {
                    Text(outputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop Down"
                    )

                }

                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = {
                        oExpanded = false
                    }) {
                    DropdownMenuItem(
                        text = {Text("Centimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Meters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.00
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Feet")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )

                    DropdownMenuItem(
                        text = {Text("Millimeters")},
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnit()
                        }
                    )
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text("$inputValue $inputUnit = $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall)
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

package com.example.unitconverter

// Necessary Android and Jetpack Compose imports
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Allows the app layout to extend into system UI areas
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Scaffold to apply system padding correctly
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        UnitConverter(paddingValues = innerPadding)
                    }
                }
            }
        }
    }
}

// Text style applied to the main heading
val textStyle = TextStyle(
    fontFamily = FontFamily.Cursive,
    color = Color.Blue,
    fontSize = 40.sp,
    fontWeight = FontWeight.Bold
)

@Composable
fun UnitConverter(paddingValues: PaddingValues) {
    // State holders for user input and selected units
    var inputValue by remember { mutableStateOf("0.0") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputValue by remember { mutableStateOf("0.0") }
    var outputUnit by remember { mutableStateOf("Meters") }

    // Dropdown menu state tracking
    var iExpanded by remember { mutableStateOf(false) } // input unit dropdown visibility
    var oExpanded by remember { mutableStateOf(false) } // output unit dropdown visibility

    // Conversion factors relative to meters (base unit)
    var conversionFactor = remember { mutableDoubleStateOf(1.00) }
    var oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    // Result of conversion
    var result by remember { mutableDoubleStateOf(0.0) }

    // Main conversion logic
    fun convertUnit() {
        // Parse input to Double or default to 0.0 if invalid
        val inputUnitDouble = inputValue.toDoubleOrNull() ?: 0.0

        // Perform conversion, normalized to meters, then to target unit
        result = (inputUnitDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0

        // Set the output value for display
        outputValue = result.toString()
    }

    // UI Layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text("Unit Converter", style = textStyle)
        Spacer(modifier = Modifier.height(22.dp))

        // User input field for numeric value
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                if (it.toIntOrNull() != null || it.toDoubleOrNull() != null) {
                    inputValue = it
                    convertUnit()
                } else if (it == "") {
                    inputValue = it
                    outputValue = "0"
                }
            },
            label = { Text("Enter Input Value") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Row for unit selection dropdowns
        Row {
            // Input unit dropdown
            Box {
                Button(
                    onClick = { iExpanded = !iExpanded }
                ) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }

                DropdownMenu(
                    expanded = iExpanded,
                    onDismissRequest = { iExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
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

            // Output unit dropdown
            Box {
                Button(
                    onClick = { oExpanded = !oExpanded }
                ) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Drop Down")
                }

                DropdownMenu(
                    expanded = oExpanded,
                    onDismissRequest = { oExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.doubleValue = 1.0
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnit()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
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

        // Display conversion result
        Text(
            "$inputValue $inputUnit = $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

// Preview function for Android Studio
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

package com.example.batterycalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val batteryPercentageInput = findViewById<EditText>(R.id.batteryPercentageInput)
        val batteryCapacityInput = findViewById<EditText>(R.id.batteryCapacityInput)
        val currentConsumptionInput = findViewById<EditText>(R.id.currentConsumptionInput)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val percentText = batteryPercentageInput.text.toString()
            val capacityText = batteryCapacityInput.text.toString()
            val consumptionText = currentConsumptionInput.text.toString()

            if (percentText.isNotEmpty() && capacityText.isNotEmpty() && consumptionText.isNotEmpty()) {
                try {
                    val batteryPercentage = percentText.toDouble()
                    val batteryCapacity = capacityText.toDouble()
                    val averageConsumption = consumptionText.toDouble()

                    if (batteryPercentage <= 0 || averageConsumption <= 0 || batteryCapacity <= 0) {
                        resultText.text = getString(R.string.error_invalid_values)
                        return@setOnClickListener
                    }

                    val remainingMah = (batteryPercentage / 100) * batteryCapacity
                    val estimatedHours = remainingMah / averageConsumption
                    val estimatedMinutes = estimatedHours * 60

                    resultText.text = getString(
                        R.string.result_text_format,
                        estimatedHours,
                        estimatedMinutes
                    )
                } catch (_: NumberFormatException) {
                    // We don't need the exception object 'e', so we use '_'
                    resultText.text = getString(R.string.error_invalid_input)
                }
            } else {
                resultText.text = getString(R.string.error_empty_fields)
            }
        }
    }
}
package com.example.calculator3dprinting

import kotlin.math.roundToInt

// PrinterData.kt
data class PrinterData(
    var name: String,
    var materialDiameter: Double,
    var price: Double,
    var depreciationTime: Double,
    var serviceCostsPerLife: Double,
    var energyConsumption: Double
) {
    val depreciation: Double
        get() = (((price + serviceCostsPerLife) / depreciationTime) * 100).roundToInt() / 100.0
}
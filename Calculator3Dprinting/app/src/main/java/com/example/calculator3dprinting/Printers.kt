package com.example.calculator3dprinting

import kotlin.math.roundToInt

class Printers(
    nameValue: String,
    materialValue: Double?,
    priceValue: Double,
    depTimeValue: Double,
    srvCostsValue: Double?,
    energyValue: Double
) {
    private var name: String = nameValue
    private var materialDiameter: Double = 0.0
    private var price: Double = priceValue
    private var depreciationTime: Double = depTimeValue
    private var serviceCosts: Double = 0.0
    private var energy: Double = energyValue
    private var depreciation: Double?

    init {
        val materialTemp: Double = materialValue ?: 0.0
        val srvTemp: Double = srvCostsValue ?: 0.0
        var depTimeValueTemp: Double = depTimeValue
        if (depTimeValue == 0.0) {
            depTimeValueTemp = -1.0
        }

        this.materialDiameter = materialTemp
        this.serviceCosts = srvTemp
        this.depreciation = (((priceValue + srvTemp) / depTimeValueTemp) * 100).roundToInt() / 100.0
    }

    fun getName(): String {
        return this.name
    }

    fun getMatDia(): Double {
        return this.materialDiameter
    }

    fun getPrice(): Double {
        return this.price
    }

    fun getDepT(): Double {
        return this.depreciationTime
    }

    fun getSvcCosts(): Double {
        return this.serviceCosts
    }

    fun getEnergy(): Double {
        return this.energy
    }

    fun getDepreciation(): Double? {
        return this.depreciation
    }

    fun setName(name: String) {
        this.name = name
    }

    fun setMatDia(matDia: Double) {
        this.materialDiameter = matDia
    }

    fun setPrice(price: Double) {
        this.price = price
    }

    fun setDepT(depT: Double) {
        if (depT == 0.0) {
            this.depreciationTime = -1.0
        } else {
            this.depreciationTime = depT
        }
    }

    fun setSvcCosts(svcCosts: Double) {
        this.serviceCosts = svcCosts
    }

    fun setEnergy(energy: Double) {
        this.energy = energy
    }

    fun printerId(printer: Printers): Int {
        return printer.hashCode()
    }
}
package com.example.calculator3dprinting

import java.lang.Exception
import kotlin.properties.Delegates

class PrinterManager {
    /* : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val materialDiameter = double("Material Diameter [mm]")
    val price = double("Price [$]")
    val depreciationTime = double("Depreciation Time [h]")
    val serviceCosts = double("Service Costs per Life [$]")
    val energy = double("Energy Consumption [kWh / h]")
    val depreciation = double("Depreciation [$ / h]")

    override val primaryKey = PrimaryKey(id, name = "PK_Printers_ID")
    */

    constructor(hash: MutableMap<Int, Printers>?) {
        this.hashPrinter = hash
    }

    val hashPrinter: MutableMap<Int, Printers>?

    fun getPrinterById(id: Int): Printers? {
        return hashPrinter?.get(id) //?: throw Exception("There are no printers by this id")
    }

    fun getIdByName(name: String): Int {
        val keys = hashPrinter?.keys
        var id = 0

        if (keys != null) {
            for (i in keys) {
                if (hashPrinter?.getValue(i)?.getName() == name) {
                    id = i
                    break
                }
            }
        } else {
            throw java.lang.Exception("There are no Printers")
        }
        return id
    }


    fun updatePrinter(
        idValue: Int,
        nameValue: String, materialValue: Double?,
        priceValue: Double, depTimeValue: Double,
        srvCostsValue: Double?, energyValue: Double
    ) {
        val materialTemp: Double = materialValue ?: 0.0
        val srvTemp: Double = srvCostsValue ?: 0.0
        var depTimeValueTemp: Double = depTimeValue
        val printer = getPrinterById(idValue)!!

        if (depTimeValue == 0.0) {
            depTimeValueTemp = -1.0
        }

        printer.setName(nameValue)
        printer.setMatDia(materialTemp)
        printer.setPrice(priceValue)
        printer.setDepT(depTimeValueTemp)
        printer.setSvcCosts(srvTemp)
        printer.setEnergy(energyValue)
    }

    fun toMutableString(printer: Printers): MutableSet<String> {
        var pSet: ArrayList<String> = arrayListOf()
        var mSet: MutableSet<String> = mutableSetOf()

        pSet.add(printer.getName())
        pSet.add(printer.getMatDia().toString())
        pSet.add(printer.getPrice().toString())
        pSet.add(printer.getDepT().toString())
        pSet.add(printer.getSvcCosts().toString())
        pSet.add(printer.getEnergy().toString())

        mSet.addAll(pSet)
        return mSet
    }


}
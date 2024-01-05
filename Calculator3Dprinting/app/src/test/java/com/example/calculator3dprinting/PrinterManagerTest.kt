package com.example.calculator3dprinting

import android.util.Log
import org.junit.Test

import org.junit.Assert.*

class PrinterManagerTest {

    @Test
    fun getPrinterById() {
        val printerMap: PrinterManager
        val id: Int

        var printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)
        id = printer.printerId(printer)
        val mMap = mutableMapOf(id to printer)
        printerMap = PrinterManager(mMap)

        assertTrue(printerMap.getPrinterById(id) == printer)
    }

    @Test
    fun getIdByName() {
        val printerMap: PrinterManager
        val id: Int

        var printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)
        id = printer.printerId(printer)
        val mMap = mutableMapOf(id to printer)
        printerMap = PrinterManager(mMap)

        assertTrue(printerMap.getIdByName(printer.getName()) == id)
    }

    @Test
    fun updatePrinter() {
        val printerMap: PrinterManager
        val id1: Int
        val id2: Int

        val printer1 = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)
        val printer2 = Printers(
            "Ultimaker Origninal", null,
            1000.00, 3000.0, 100.0, 0.150
        )
        id1 = printer1.printerId(printer1)
        id2 = printer2.printerId(printer2)
        val mMap = mutableMapOf(id1 to printer1)
        mMap[id2] = Printers("", 0.0, 0.0, 0.0, 0.0, 0.0)
        printerMap = PrinterManager(mMap)

        printerMap.updatePrinter(
            id2, "Ultimaker Origninal", null,
            1000.00, 3000.0, 100.0, 0.150
        )

        assertTrue(printerMap.getPrinterById(id2)?.getName()== printer2.getName()
                && printerMap.getPrinterById(id2)?.getMatDia()  == printer2.getMatDia()
                && printerMap.getPrinterById(id2)?.getPrice() == printer2.getPrice()
                && printerMap.getPrinterById(id2)?.getDepT() == printer2.getDepT()
                && printerMap.getPrinterById(id2)?.getSvcCosts() == printer2.getSvcCosts()
                && printerMap.getPrinterById(id2)?.getEnergy() == printer2.getEnergy())
    }
}
package com.example.calculator3dprinting

import org.junit.Test

import org.junit.Assert.*
import kotlin.math.roundToInt

class PrintersTest {

    @Test
    fun getName() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getName() == "Cetus")
    }

    @Test
    fun getMatDia() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getMatDia() == 1.75)
    }

    @Test
    fun getPrice() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getPrice() == 450.00)
    }

    @Test
    fun getDepT() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getDepT() == 5000.0)
    }

    @Test
    fun getSvcCosts() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getSvcCosts() == 100.0)
    }

    @Test
    fun getEnergy() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getEnergy() == 0.150)
    }

    @Test
    fun getDepreciation() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        assertTrue(printer.getDepreciation() == (((printer.getPrice() + printer.getSvcCosts())
                / printer.getDepT()) * 100).roundToInt() / 100.0)
    }

    @Test
    fun setName() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setName("blake")
        assertTrue(printer.getName() == "blake")
    }

    @Test
    fun setMatDia() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setMatDia(0.0)
        assertTrue(printer.getMatDia() == 0.0)
    }

    @Test
    fun setPrice() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setPrice(0.0)
        assertTrue(printer.getPrice() == 0.0)
    }

    @Test
    fun setDepT() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setDepT(0.0)
        assertTrue(printer.getDepT() == -1.0)
    }

    @Test
    fun setSvcCosts() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setSvcCosts(0.0)
        assertTrue(printer.getSvcCosts() == 0.0)
    }

    @Test
    fun setEnergy() {
        val printer = Printers("Cetus", 1.75, 450.00,
            5000.0, 100.0, 0.150)

        printer.setEnergy(0.0)
        assertTrue(printer.getEnergy() == 0.0)
    }
}
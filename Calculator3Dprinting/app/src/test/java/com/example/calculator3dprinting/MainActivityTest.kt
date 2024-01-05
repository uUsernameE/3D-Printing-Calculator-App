package com.example.calculator3dprinting

import android.widget.EditText
import android.widget.TextView
import org.junit.Test

import org.junit.Assert.*

class MainActivityTest {

    @Test
    fun onClick() {
        val spoolPrice: Double = 20.0
        val spoolWeight: Double = 1.0
        val printWeight: Double = 14.0
        val hourlyRate: Double = 15.0
        val workHours: Double = 15.0
        val result: Double

        val pricePerSpool = spoolPrice / spoolWeight
        val printPrice = printWeight* pricePerSpool / 1000
        val labor = hourlyRate * workHours / 60
        val sum = (printPrice + labor)

        assertEquals(4.03, sum, 0.0)
        assertFalse(4.1 == sum)
    }
}
package com.example.calculator3dprinting

import android.content.Context
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.jetbrains.exposed.sql.transactions.TransactionManager

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.sql.Connection

@RunWith(AndroidJUnit4::class)
class DatabaseManagerTest {
    /*
    // getter for test application context
    private val context: Context?
    get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun useAppContext() {
        assertEquals("com.example.calculator3dprinting", context?.packageName)
    }

    @Test
    fun dbConnect() {
        val db = DatabaseManager(context)
        db.connect()
        assertTrue(db.isConnected())
    }

    @Test
    fun dbUse() {
        val db = DatabaseManager(context)
        db.useDB()
    }
    */
}
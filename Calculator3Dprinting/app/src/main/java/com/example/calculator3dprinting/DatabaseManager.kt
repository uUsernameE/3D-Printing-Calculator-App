package com.example.calculator3dprinting

import android.content.Context
import android.util.Log
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Math.PI
import java.sql.Connection
import kotlin.math.pow
import kotlin.math.roundToInt

class DatabaseManager {
/*
    constructor(context : Context? = null) {
        this.context = context
    }
    var context : Context? = null

    private val TAG = "DatabaseManager"

    private val user
        get() = context!!.getString(R.string.databaseUser)
    private val host
        get() = context!!.getString(R.string.databaseHost)
    private val password
        get() = context!!.getString(R.string.databasePassword)
    private val port
        get() = Integer.parseInt(context!!.getString(R.string.databasePort))
    private val name
        get() = context!!.getString(R.string.databaseName)

    private var connected = false
    private var initialized = false

    fun connect() {
        if (!connected) {
            val url = "jdbc:postgresql://$host:$port/$name"
            val driver = "org.postgresql.Driver";
            Database.connect(url, driver, user, password)
            connected = true
        }
    }

    // dbc:postgresql://database-1.cwmvkzxze1wc.us-east-2.rds.amazonaws.com:5432/database-1

    fun isConnected(): Boolean {return connected}

    private fun createTables() {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Printers)
        }
    }

    private fun dropTables() {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.drop(Printers)
        }
    }

    /*
    fun showTables() {
        connect()
        transaction {
            addLogger(StdOutSqlLogger)
            val query = "show tables"
            val conn = TransactionManager.current().exec(query)
        }
    }
    */

    fun insertPrinter(
        nameValue: String, materialValue: Double?,
        priceValue: Double, depTimeValue: Double,
        srvCostsValue: Double?, energyValue: Double
    ): Int {
        val materialTemp: Double = materialValue ?: 0.0
        val srvTemp: Double = srvCostsValue ?: 0.0

        return Printers.insert {
            it[name] = nameValue
            it[materialDiameter] = materialTemp
            it[price] = priceValue
            it[depreciationTime] = depTimeValue
            it[serviceCosts] = srvTemp
            it[energy] = energyValue
            it[depreciation] =
                (((priceValue + srvTemp) / depTimeValue) * 100).roundToInt() / 100.0
        } get Printers.id
    }

    private fun insertMaterial(
        manuValue: String, diamValue: Double,
        spoolPriceValue: Double, spoolSizeValue: Double,
        densityValue: Double, nozzelValue: Int?, bedValue: Int?
    ): Int {
        val nozzelTem: Int = nozzelValue ?: 0
        val bedTem: Int = bedValue ?: 0

        return Materials.insert {
            it[manufacturer] = manuValue
            it[diameter] = diamValue
            it[spoolPrice] = spoolPriceValue
            it[spoolSize] = spoolSizeValue
            it[density] = densityValue
            it[nozzelTemp] = nozzelTem
            it[bedTemp] = bedTem
            it[length] = (spoolSizeValue * 4 /
                    (densityValue * PI * (diamValue / 10.0).pow(2.0)) * 10.0).roundToInt()
            it[price] = spoolPriceValue / spoolSizeValue
        } get Materials.id
    }

    fun getPrinterIdByName(printerName: String): Int {
        return Printers.select { Printers.name eq printerName }.single()[Printers.id]
    }

    private fun getMaterialIdByName(materialName: String): Int {
        return Materials.select {Materials.manufacturer eq materialName}.single()[Materials.id]
    }

    /*
    fun getPrinterIds(name: String): List<String> {
        connect()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        val data
    }*/

    fun getPrinterNames(): List<String> {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        val res = mutableListOf<String>()
        transaction {
            for(printer in Printers.selectAll()) {
                res.add(printer[Printers.name])
            }
        }
        return res
    }

    /*
    fun getMaterialNames(): List<String> {
        connect()
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE

        var res = mutableListOf<String>()
        transaction {
            for (materials in Materials.selectAll()) {
                res.add(materials[Materials.manufacturer])
            }
        }
        return res
    }*/

    fun updatePrinters(idValue: Int,
        nameValue: String, materialValue: Double?,
        priceValue: Double, depTimeValue: Double,
        srvCostsValue: Double?, energyValue: Double
    ) {
        val materialTemp: Double = materialValue ?: 0.0
        val srvTemp: Double = srvCostsValue ?: 0.0

        transaction {
            Printers.update({Printers.id eq idValue}) {
                it[name] = nameValue
                it[materialDiameter] = materialTemp
                it[price] = priceValue
                it[depreciationTime] = depTimeValue
                it[serviceCosts] = srvTemp
                it[energy] = energyValue
                it[depreciation] =
                    (((priceValue + srvTemp) / depTimeValue) * 100).roundToInt() / 100.0
            }
        }
    }

    fun updateMaterials(idValue: Int,
        manuValue: String, diamValue: Double,
        spoolPriceValue: Double, spoolSizeValue: Double,
        densityValue: Double, nozzelValue: Int?, bedValue: Int?
    ) {
        val nozzelTem: Int = nozzelValue ?: 0
        val bedTem: Int = bedValue ?: 0

        transaction {
            Materials.update({ Materials.id eq idValue }) {
                it[manufacturer] = manuValue
                it[diameter] = diamValue
                it[spoolPrice] = spoolPriceValue
                it[spoolSize] = spoolSizeValue
                it[density] = densityValue
                it[nozzelTemp] = nozzelTem
                it[bedTemp] = bedTem
                it[length] = (spoolSizeValue * 4 /
                        (densityValue * PI * (diamValue / 10.0).pow(2.0)) * 10.0).roundToInt()
                it[price] = spoolPriceValue / spoolSizeValue
            }
        }
    }

    fun deletePrinter(idValue: Int/*,
          nameValue: String, materialValue: Double?,
          priceValue: Double, depTimeValue: Double,
          srvCostsValue: Double?, energyValue: Double */
    ) {
        Printers.deleteWhere { Printers.id eq idValue }
    }

    fun deleteMaterial(idValue: Int/*,
                        manuValue: String, diamValue: Double,
                        spoolPriceValue: Double, spoolSizeValue: Double,
                        densityValue: Double, nozzelValue: Int?, bedValue: Int? */
    ) {
        Materials.deleteWhere { Materials.id eq idValue }
    }

    fun initialize() {
        connect()
        dropTables()
        createTables()
        if(!initialized) {
            transaction {
                insertPrinter(
                    "Cetus", 1.75, 450.00,
                    5000.0, 100.0, 0.150
                )
                insertPrinter(
                    "Ultimaker Origninal", null,
                    1000.00, 3000.0, 100.0, 0.150
                )
                insertMaterial(
                    "das Filament - PLA - Black", 1.75, 16.00,
                    0.80, 1.24, null, null
                )
                insertMaterial(
                    "Polymaker - PC - Max", 1.75, 45.00,
                    .75, 1.19, 260, null
                )
                insertMaterial(
                    "Formfutura - ABSpro", 1.75, 35.00,
                    0.50, 1.05, 260, 115
                )
            }
            initialized = true
        }
    }

    fun useDB() {
        initialize()
        transaction {
            Printers.update({Printers.id eq
                    getPrinterIdByName("Ultimaker Original")}) {
                it[materialDiameter] = 1.75
            }

        }
    }
*/
}
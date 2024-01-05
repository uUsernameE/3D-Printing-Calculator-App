package com.example.calculator3dprinting

import org.jetbrains.exposed.sql.Table

object Materials: Table() {
    val id = integer("id").autoIncrement()
    val manufacturer = varchar("Manufacturer - Type - (Color)", 50)
    val diameter = double("Diameter [mm]")
    val spoolPrice = double("Spool Price [$]")
    val spoolSize = double("Spool Size [kg]")
    val density = double("Density [g / cm^3]")
    val nozzelTemp = integer("Nozzel Temp. [C] ")
    val bedTemp = integer("Bed Temp. [C]")
    val length = integer("Length per Roll [m]")
    val price = double("Price [$ / kg]")
}
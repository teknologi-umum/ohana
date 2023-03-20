package com.teknologiumum.models

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.duration

object PingTarget : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 255)
    val description = varchar("description", 255)
    val address = text("address")
    val interval = duration("interval")
    val timeout = duration("timeout")

    override val primaryKey = PrimaryKey(id)
}
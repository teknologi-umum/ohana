package com.teknologiumum.models

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.*
object PingHistory : Table() {
    val id = integer("id").autoIncrement()
    val duration = duration("duration")
    val statusCode = integer("status_code")
    val timestamp = datetime("timestamp")
    val targetId = (integer("targetId") references PingTarget.id)

    override val primaryKey = PrimaryKey(id)
}
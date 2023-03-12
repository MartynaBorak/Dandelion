package com.example.dandelion.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "dayLog")
data class DayLog (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val energy: Int,
    val happiness: Int,
    val anger: Int,
    val stress: Int,
    val sleep: Int,
    val period: Boolean,
    val journal: String
)
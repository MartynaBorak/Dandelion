package com.example.dandelion.ui.logs

import com.example.dandelion.data.DayLog
import java.text.SimpleDateFormat
import java.util.*

data class LogUiState(
    val id: Int = 0,
    val dateString: String = "",
    val energy: Float = 3f,
    val happiness: Float = 3f,
    val stress: Float = 3f,
    val sleep: Float = 3f,
    val period: Boolean = false,
    val journal: String = "",

    val day: String = "",
    val month: String = "",
    val year: String = "",
    val actionEnabled: Boolean = false
)

fun LogUiState.toDayLog(): DayLog = DayLog(
    id = id,
    date = convertToDate(dateString),
    energy = energy.toInt(),
    happiness = happiness.toInt(),
    stress = stress.toInt(),
    sleep = sleep.toInt(),
    period = period,
    journal = journal
)

fun DayLog.toLogUiState(actionEnabled: Boolean = false): LogUiState = LogUiState(
    id = id,
    dateString = convertToString(date),
    energy = energy.toFloat(),
    happiness = happiness.toFloat(),
    stress = stress.toFloat(),
    sleep = sleep.toFloat(),
    period = period,
    journal = journal,
    actionEnabled = actionEnabled
)

fun LogUiState.isValid() : Boolean {
    return day.length<3 && month.length<3 && year.length<5
}

fun LogUiState.isDateValid() : Boolean {
    try {
        convertToDate(dateString)
    } catch (e: Exception) {
        return false
    }
    return true
}

private fun convertToDate(dateString: String): Date {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.parse(dateString)!!
}

private fun convertToString(date: Date): String {
    val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return format.format(date)
}
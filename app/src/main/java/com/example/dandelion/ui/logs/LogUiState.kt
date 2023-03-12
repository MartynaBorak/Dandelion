package com.example.dandelion.ui.logs

import com.example.dandelion.data.DayLog
import java.util.Date

data class LogUiState(
    val id: Int = 0,
    val date: Date = Date(),
    val energy: Int = 3,
    val happiness: Int = 3,
    val anger: Int = 3,
    val stress: Int = 3,
    val sleep: Int = 3,
    val period: Boolean = false,
    val journal: String = "",
    val actionEnabled: Boolean = false
)

fun LogUiState.toDayLog(): DayLog = DayLog(
    id = id,
    date = date,
    energy = energy,
    happiness = happiness,
    anger = anger,
    stress = stress,
    sleep = sleep,
    period = period,
    journal = journal
)

fun LogUiState.toLogUiState(actionEnabled: Boolean = false): LogUiState = LogUiState(
    id = id,
    date = date,
    energy = energy,
    happiness = happiness,
    anger = anger,
    stress = stress,
    sleep = sleep,
    period = period,
    journal = journal,
    actionEnabled = actionEnabled
)
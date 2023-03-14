package com.example.dandelion.ui.logs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.dandelion.data.LogsRepository

class LogEntryViewModel(private val logsRepository: LogsRepository) : ViewModel() {
    var logUiState by mutableStateOf(LogUiState())
        private set

    fun updateUiState(newLogUiState: LogUiState) {
        logUiState = newLogUiState.copy( actionEnabled = newLogUiState.isValid() )
        logUiState = logUiState.copy(dateString = buildDateString(
            day = logUiState.day, month = logUiState.month, year = logUiState.year )
        )
    }

    suspend fun saveLog() {
        if(logUiState.isDateValid()) {
            logsRepository.insertLog(logUiState.toDayLog())
        }
    }

    private fun buildDateString(day: String, month: String, year: String): String {
        return if(day.length==1) "0$day" else {day} + "/" + if(month.length==1) "0$month" else {"$month"} + "/$year"
    }
}
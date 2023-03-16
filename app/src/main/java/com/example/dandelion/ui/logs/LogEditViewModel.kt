package com.example.dandelion.ui.logs

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandelion.data.LogsRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LogEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val logsRepository: LogsRepository
) : ViewModel() {

    private val logId: Int = checkNotNull(savedStateHandle[LogEditDestination.logIdArg])
    var logUiState by mutableStateOf(LogUiState())
        private set

    init {
        viewModelScope.launch {
            logUiState = logsRepository.getLogStream(logId)
                .filterNotNull()
                .first()
                .toLogUiState(dateChangeEnabled = false)
        }
    }

    fun updateUiState(newLogUiState: LogUiState) {
        logUiState = newLogUiState.copy( dateChangeEnabled = false )
    }

    suspend fun updateLog() {
        if(logUiState.isDateValid()) {
            logsRepository.updateLog(logUiState.toDayLog())
        }
    }

    private fun buildDateString(day: String, month: String, year: String): String {
        return if(day.length==1) "0$day" else {day} + "/" + if(month.length==1) "0$month" else {"$month"} + "/$year"
    }
}
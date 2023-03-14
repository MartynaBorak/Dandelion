package com.example.dandelion.ui.logs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandelion.data.DayLog
import com.example.dandelion.data.LogsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HistoryViewModel(private val logsRepository: LogsRepository) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val historyUiState: StateFlow<HistoryUiState> = logsRepository.getAllLogsStream()
        .map { HistoryUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HistoryUiState()
        )
}

data class HistoryUiState(val logsList: List<DayLog> = listOf())
package com.example.dandelion.ui.logs

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dandelion.data.LogsRepository
import kotlinx.coroutines.flow.*

class LogDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val logsRepository: LogsRepository
) : ViewModel() {

    private val logId: Int = checkNotNull(savedStateHandle[LogDetailsDestination.logIdArg])
    val uiState: StateFlow<LogUiState> = logsRepository.getLogStream(logId)
        .filterNotNull()
        .map {
            it.toLogUiState(actionEnabled = true)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = LogUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    suspend fun deleteLog() {
        logsRepository.deleteLog(uiState.value.toDayLog())
    }
}
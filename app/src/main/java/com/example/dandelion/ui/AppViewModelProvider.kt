package com.example.dandelion.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dandelion.DandelionApplication
import com.example.dandelion.ui.logs.HistoryViewModel
import com.example.dandelion.ui.logs.LogDetailsViewModel
import com.example.dandelion.ui.logs.LogEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for LogEntryViewModel
        initializer {
            LogEntryViewModel(
                DandelionApplication().container.logsRepository
            )
        }

        // Initializer for LogHistoryViewModel
        initializer {
            HistoryViewModel(
                DandelionApplication().container.logsRepository
            )
        }

        // Initializer for LogEditViewModel
        //

        // Initializer for LogDetailsViewModel
        initializer {
            LogDetailsViewModel(
                this.createSavedStateHandle(),
                DandelionApplication().container.logsRepository
            )
        }
    }
}

fun CreationExtras.DandelionApplication(): DandelionApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as DandelionApplication)
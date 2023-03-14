package com.example.dandelion.ui

import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dandelion.DandelionApplication
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
        //

        // Initializer for LogEditViewModel
        //

        // Initializer for LogDetailsViewModel
        //
    }
}
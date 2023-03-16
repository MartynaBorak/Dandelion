package com.example.dandelion.ui.logs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dandelion.ui.AppViewModelProvider
import com.example.dandelion.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object LogEditDestination : NavigationDestination {
    override val route = "edit"
    const val logIdArg = "logId"
    val routeWithArgs = "${LogEditDestination.route}/{$logIdArg}"
}

@Composable
fun LogEditScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LogEditViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    LogInputForm(
        logUiState = viewModel.logUiState,
        onLogValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.updateLog()
                navigateBack()
            }
        },
        navigateBack = navigateBack
    )
}
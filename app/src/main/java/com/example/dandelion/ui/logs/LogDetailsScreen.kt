package com.example.dandelion.ui.logs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dandelion.ui.AppViewModelProvider
import com.example.dandelion.ui.navigation.NavigationDestination
import kotlinx.coroutines.launch

object LogDetailsDestination : NavigationDestination {
    override val route = "details"
    const val logIdArg = "logId"
    val routeWithArgs = "$route/{$logIdArg}"
}

@Composable
fun LogDetailsScreen(
    navigateToEdit: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: LogDetailsViewModel = viewModel(factory = AppViewModelProvider.Factory),
    modifier: Modifier = Modifier
){
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(text = uiState.value.dateString, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Energy", fontSize = 18.sp)
                Text(text = "${uiState.value.energy.toInt()}/5", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Happiness", fontSize = 18.sp)
                Text(text = "${uiState.value.happiness.toInt()}/5", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Stress", fontSize = 18.sp)
                Text(text = "${uiState.value.stress.toInt()}/5", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Sleep quality", fontSize = 18.sp)
                Text(text = "${uiState.value.sleep.toInt()}/5", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = "Period", fontSize = 18.sp)
                if(uiState.value.period) {
                    Text(text = "Yes", fontSize = 18.sp)
                } else {
                    Text(text = "Yes", fontSize = 18.sp)
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = uiState.value.journal,
                onValueChange = {},
                enabled = false,
                singleLine = false,
                maxLines = 6,
                label = { Text("Journal") }
            )

            var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    onClick = navigateToEdit,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
                OutlinedButton(
                    onClick = navigateBack,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Back")
                }
                OutlinedButton(
                    onClick = { deleteConfirmationRequired = true },
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Delete")
                }
                if(deleteConfirmationRequired) {
                    DeleteConfirmationDialog(
                        onDeleteConfirm = {
                            deleteConfirmationRequired = false
                            coroutineScope.launch{
                                viewModel.deleteLog()
                                navigateBack()
                            }
                        },
                        onDeleteCancel = { deleteConfirmationRequired = false }
                    )
                }
            }
        }
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { /* Do nothing */ },
        title = { Text("Attention") },
        text = { Text("Are you sure you want to delete?") },
        modifier = modifier.padding(16.dp),
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text("No")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text("Yes")
            }
        }
    )
}
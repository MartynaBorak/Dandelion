package com.example.dandelion.ui.logs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dandelion.ui.AppViewModelProvider
import com.example.dandelion.ui.navigation.NavigationDestination
import com.example.dandelion.ui.theme.DandelionTheme
import kotlinx.coroutines.launch

object LogEntryDestination : NavigationDestination {
    override val route = "entry"
}

@Composable
fun LogEntryScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LogEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    LogInputForm(
        logUiState = viewModel.logUiState,
        onLogValueChange = viewModel::updateUiState,
        onSaveClick = {
            coroutineScope.launch {
                viewModel.saveLog()
                navigateBack()
            }
        },
        navigateBack = navigateBack
    )
}

@Composable
fun LogInputForm(
    logUiState: LogUiState,
    onLogValueChange: (LogUiState) -> Unit,
    onSaveClick: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier
){
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                OutlinedTextField(
                    value = logUiState.day,
                    onValueChange = { onLogValueChange(logUiState.copy(day=it)) },
                    label = { Text("Day") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedTextField(
                    value = logUiState.month,
                    onValueChange = { onLogValueChange(logUiState.copy(month=it)) },
                    label = { Text("Month") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(80.dp)
                )
                OutlinedTextField(
                    value = logUiState.year,
                    onValueChange = { onLogValueChange(logUiState.copy(year=it)) },
                    label = { Text("Year") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.width(110.dp)
                )
                Text(text = logUiState.dateString, fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

        //ParameterWithSlider(title = "Energy")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Energy: ", fontSize = 18.sp)
                Text(
                    text = logUiState.energy.toInt().toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(2.dp)
                )
            }
            Slider(
                value = logUiState.energy,
                onValueChange = { onLogValueChange(logUiState.copy(energy = it)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                steps = 4,
                valueRange = 1f..5f,
                onValueChangeFinished = {}
            )
            Spacer(modifier = Modifier.height(4.dp))

        //ParameterWithSlider(title = "Happiness")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Happiness: ", fontSize = 18.sp)
                Text(
                    text = logUiState.happiness.toInt().toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(2.dp)
                )
            }
            Slider(
                value = logUiState.happiness,
                onValueChange = { onLogValueChange(logUiState.copy(happiness = it)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                steps = 4,
                valueRange = 1f..5f,
                onValueChangeFinished = {}
            )
            Spacer(modifier = Modifier.height(4.dp))

        //ParameterWithSlider(title = "Stress")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Stress: ", fontSize = 18.sp)
                Text(
                    text = logUiState.stress.toInt().toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(2.dp)
                )
            }
            Slider(
                value = logUiState.stress,
                onValueChange = { onLogValueChange(logUiState.copy(stress = it)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                steps = 4,
                valueRange = 1f..5f,
                onValueChangeFinished = {}
            )
            Spacer(modifier = Modifier.height(4.dp))

        //ParameterWithSlider(title = "Sleep quality")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Sleep quality: ", fontSize = 18.sp)
                Text(
                    text = logUiState.sleep.toInt().toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(2.dp)
                )
            }
            Slider(
                value = logUiState.sleep,
                onValueChange = { onLogValueChange(logUiState.copy(sleep = it)) },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                steps = 4,
                valueRange = 1f..5f,
                onValueChangeFinished = {}
            )
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Period")
                Checkbox(
                    checked = logUiState.period,
                    onCheckedChange = { onLogValueChange(logUiState.copy(period = it)) }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = logUiState.journal,
                onValueChange = { onLogValueChange(logUiState.copy(journal = it)) },
                enabled = true,
                singleLine = false,
                maxLines = 4,
                label = { Text("Journal") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalArrangement = Arrangement.SpaceAround
            ){
                Button(
                    onClick = onSaveClick,
                    enabled = logUiState.actionEnabled,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
                OutlinedButton(
                    onClick = navigateBack,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Cancel")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EntryLogPreview(){
    DandelionTheme {
        LogEntryScreen(navigateBack = {})
    }
}
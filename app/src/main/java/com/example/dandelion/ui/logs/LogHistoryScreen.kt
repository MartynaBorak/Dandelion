package com.example.dandelion.ui.logs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dandelion.DandelionTopAppBar
import com.example.dandelion.data.DayLog
import com.example.dandelion.ui.AppViewModelProvider
import com.example.dandelion.ui.navigation.NavigationDestination

object LogHistoryDestination : NavigationDestination {
    override val route = "history"
}

@Composable
fun LogHistoryScreen(
    viewModel: HistoryViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onTabSelected: (NavigationDestination) -> Unit,
    currentScreen: NavigationDestination,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val historyUiState by viewModel.historyUiState.collectAsState()
    Scaffold(
        topBar = { DandelionTopAppBar(
            onTabSelected = onTabSelected,
            currentScreen = currentScreen
        ) }
    ){ paddingValues ->
        if(historyUiState.logsList.isEmpty()) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {
                Text("No logs to show")
            }
        } else {
            LazyColumn(
                modifier = modifier
                    .padding(paddingValues)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = historyUiState.logsList,
                    key = { it.id }
                ) {
                    LogItem(log = it, onLogClick = {})
                }
            }
        }

    }
}

@Composable
fun LogItem(
    log: DayLog,
    onLogClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onLogClick }
    ) {
        Column(
            modifier = Modifier
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(text = convertToString(log.date), fontSize = 18.sp)
                Text(
                    text = averageScore(log).toString(),
                    fontSize = 18.sp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.primary,
                            shape = RoundedCornerShape(2.dp)
                        )
                        .padding(2.dp)
                )
            }
        }
    }
}

fun averageScore(log: DayLog): Float {
    return (log.energy + log.happiness + log.sleep + 6-log.stress).toFloat()/4
}

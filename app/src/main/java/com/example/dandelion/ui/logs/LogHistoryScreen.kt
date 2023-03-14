package com.example.dandelion.ui.logs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dandelion.DandelionTopAppBar
import com.example.dandelion.data.DayLog
import com.example.dandelion.ui.navigation.NavigationDestination

object LogHistoryDestination : NavigationDestination {
    override val route = "history"
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun LogHistoryScreen(
    onTabSelected: (NavigationDestination) -> Unit,
    currentScreen: NavigationDestination,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = { DandelionTopAppBar(
            onTabSelected = onTabSelected,
            currentScreen = currentScreen
        ) }
    ){ paddingValues ->
        //Text(text = "This is the history screen", modifier = modifier.padding(paddingValues))
        LazyColumn(
            modifier = modifier.padding(paddingValues).fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listOf<DayLog>()/*TODO: lista logów*/) {
                LogItem(log = it, onLogClick = {})
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

}

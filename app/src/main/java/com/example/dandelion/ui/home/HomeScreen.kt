package com.example.dandelion.ui.home

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dandelion.DandelionTopAppBar
import com.example.dandelion.ui.navigation.NavigationDestination
import androidx.compose.material.Text
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import com.example.dandelion.ui.theme.LightOrange

object HomeDestination : NavigationDestination {
    override val route = "home"
}

@Composable
fun HomeScreen(
    onTabSelected: (NavigationDestination) -> Unit,
    currentScreen: NavigationDestination,
    navigateToLogEntry: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = { DandelionTopAppBar(
            onTabSelected = onTabSelected,
            currentScreen = currentScreen
        ) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navigateToLogEntry },
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add log entry",
                    tint = LightOrange
                )
            }
        }
    ){ paddingValues ->
        Text(text = "This is the home screen", modifier = modifier.padding(paddingValues))
    }
}
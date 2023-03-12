package com.example.dandelion.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dandelion.DandelionTopAppBar
import com.example.dandelion.ui.navigation.NavigationDestination
import androidx.compose.material.Text

object HomeDestination : NavigationDestination {
    override val route = "home"
}

@Composable
fun HomeScreen(
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
        Text(text = "This is the home screen", modifier = modifier.padding(paddingValues))
    }
}
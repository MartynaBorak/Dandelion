package com.example.dandelion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dandelion.ui.home.HomeDestination
import com.example.dandelion.ui.home.HomeScreen
import com.example.dandelion.ui.logs.*

@Composable
fun DandelionNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    var currentScreen: NavigationDestination by remember { mutableStateOf(HomeDestination) }
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ){
        composable(route = HomeDestination.route){
            HomeScreen(
                onTabSelected = { newScreen -> navController.navigate(newScreen.route).also{ currentScreen=newScreen } },
                currentScreen = currentScreen,
                navigateToLogEntry = { navController.navigate(LogEntryDestination.route) }
            )
        }
        composable(route = LogEntryDestination.route){
            LogEntryScreen()
        }
        composable(route = LogHistoryDestination.route){
            LogHistoryScreen(
                onTabSelected = { newScreen -> navController.navigate(newScreen.route).also { currentScreen=newScreen } },
                currentScreen = currentScreen
            )
        }
        composable(route = LogDetailsDestination.route){
            LogDetailsScreen()
        }
        composable(route = LogEditDestination.route){
            LogEditScreen()
        }
    }
}
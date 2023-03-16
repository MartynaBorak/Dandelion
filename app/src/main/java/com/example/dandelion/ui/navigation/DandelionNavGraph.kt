package com.example.dandelion.ui.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
            LogEntryScreen(
                navigateBack = {navController.popBackStack() }
            )
        }
        composable(route = LogHistoryDestination.route){
            LogHistoryScreen(
                onTabSelected = { newScreen -> navController.navigate(newScreen.route).also { currentScreen=newScreen } },
                currentScreen = currentScreen,
                navigateToDetails = {
                    navController.navigate("${LogDetailsDestination.route}/${it}")
                }
            )
        }
        composable(
            route = LogDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(LogDetailsDestination.logIdArg) {
                type = NavType.IntType
            })
        ){
            LogDetailsScreen(
                navigateBack = {navController.popBackStack()},
                navigateToEdit = {
                    navController.navigate("${LogEditDestination.route}/${it}")
                }
            )
        }
        composable(
            route = LogEditDestination.routeWithArgs,
            arguments = listOf(navArgument(LogEditDestination.logIdArg) {
                type = NavType.IntType
            })
        ){
            LogEditScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
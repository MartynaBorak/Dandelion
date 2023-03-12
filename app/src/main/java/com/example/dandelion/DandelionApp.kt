package com.example.dandelion

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dandelion.ui.navigation.DandelionNavHost
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.dandelion.ui.navigation.NavigationDestination
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.Surface
import com.example.dandelion.ui.home.HomeDestination
import com.example.dandelion.ui.logs.LogHistoryDestination

@Composable
fun DandelionApp(navController: NavHostController = rememberNavController()){
    DandelionNavHost(navController = navController)
}

@Composable
fun DandelionTopAppBar(
    onTabSelected: (NavigationDestination) -> Unit,
    currentScreen: NavigationDestination,
    modifier: Modifier = Modifier
) {
    Column(Modifier.fillMaxWidth()) {
        TopAppBar(
            content = {
                Column {
                    Row {
                        Text(
                            text = stringResource(id = R.string.app_name),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier
                                .padding(start = 10.dp)
                        )
                    }
                }
            },
            modifier = modifier,
            backgroundColor = MaterialTheme.colors.primary
        )
        Row {
            TabLayout(
                allScreens = listOf(HomeDestination, LogHistoryDestination),
                onTabSelected = onTabSelected,
                currentScreen = currentScreen
            )
        }
    }
}

@Composable
fun TabLayout(
    allScreens: List<NavigationDestination>,
    onTabSelected: (NavigationDestination) -> Unit,
    currentScreen: NavigationDestination
) {
    TabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = allScreens.indexOf(currentScreen),
        backgroundColor = MaterialTheme.colors.primary,
        tabs = {
            Tab(
                selected = currentScreen==HomeDestination,
                onClick = { onTabSelected(HomeDestination) },
                text = { Text(HomeDestination.route) }
            )
            Tab(
                selected = currentScreen==LogHistoryDestination,
                onClick = { onTabSelected(LogHistoryDestination) },
                text = { Text(LogHistoryDestination.route) }
            )
        }
    )
}
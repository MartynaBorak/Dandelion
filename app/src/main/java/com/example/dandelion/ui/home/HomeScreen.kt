package com.example.dandelion.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.dandelion.DandelionTopAppBar
import com.example.dandelion.ui.navigation.NavigationDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dandelion.R
import com.example.dandelion.ui.theme.DandelionTheme
import com.example.dandelion.ui.theme.BlackBrown

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
                onClick = navigateToLogEntry,
                modifier = Modifier.navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add log entry"
                )
            }
        }
    ){ paddingValues ->
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.dandelions),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .align(Alignment.Start)
            ){
                Text(
                    text = "You're a dandelion",
                    modifier = modifier.padding(paddingValues),
                    color = BlackBrown,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = stringResource(id = R.string.dandelions1),
                    modifier = modifier.padding(paddingValues),
                    color = BlackBrown,
                    fontSize = 21.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.dandelions2),
                    modifier = modifier.padding(paddingValues),
                    color = BlackBrown,
                    fontSize = 21.sp
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = stringResource(id = R.string.dandelions3),
                    modifier = modifier.padding(paddingValues),
                    color = BlackBrown,
                    fontSize = 21.sp
                )
            }
        }
    }
}
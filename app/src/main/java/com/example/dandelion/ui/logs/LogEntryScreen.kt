package com.example.dandelion.ui.logs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dandelion.ui.navigation.NavigationDestination
import com.example.dandelion.ui.theme.DandelionTheme

object LogEntryDestination : NavigationDestination {
    override val route = "entry"
}

@Composable
fun LogEntryScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
){
    LogInputForm(onNavigateUp)
}

@Composable
fun LogInputForm(
    onNavigateUp: () -> Unit
){
    var period by remember { mutableStateOf(false) }
    var journal by remember { mutableStateOf("") }

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
            Text("Day of week, dd mmm yyyy", fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Divider()

            ParameterWithSlider(title = "Energy")
            ParameterWithSlider(title = "Happiness")
            ParameterWithSlider(title = "Anger")
            ParameterWithSlider(title = "Stress")
            ParameterWithSlider(title = "Sleep quality")
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
                    checked = period,
                    onCheckedChange = { period = it })
            }
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                value = journal,
                onValueChange = { journal = it },
                enabled = true,
                singleLine = false,
                maxLines = 10,
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
                    onClick = {},
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Save")
                }
                OutlinedButton(
                    onClick = onNavigateUp,
                    enabled = true,
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text("Cancel")
                }
            }
        }
    }
}

@Composable
fun ParameterWithSlider(
    title: String
){
    var position by remember { mutableStateOf(3f) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = "$title: ", fontSize = 18.sp)
        Text(
            text = position.toInt().toString(),
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
        value = position,
        onValueChange = { position = it },
        modifier = Modifier.fillMaxWidth(),
        enabled = true,
        steps = 4,
        valueRange = 1f..5f,
        onValueChangeFinished = {}
    )
    Spacer(modifier = Modifier.height(4.dp))
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EntryLogPreview(){
    DandelionTheme {
        LogEntryScreen(onNavigateUp = {})
    }
}
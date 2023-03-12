package com.example.dandelion.ui.logs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dandelion.ui.navigation.NavigationDestination
import com.example.dandelion.ui.theme.DandelionTheme

object LogEntryDestination : NavigationDestination {
    override val route = "entry"
}

@Composable
fun LogEntryScreen(
    modifier: Modifier = Modifier
){
    LogInputForm()
}

@Composable
fun LogInputForm(){
    var position by remember { mutableStateOf(3f) }
    var period by remember { mutableStateOf(false) }
    var journal by remember { mutableStateOf("") }

    Surface(
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Day of week, dd mmm yyyy")
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Energy")
            Slider(
                value = position,
                onValueChange = { position = it },
                modifier = Modifier.fillMaxWidth(),
                enabled = true,
                steps = 4,
                valueRange = 1f..5f,
                onValueChangeFinished = {}
            )
            Text(text = position.toInt().toString())
            Spacer(modifier = Modifier.height(8.dp))

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
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                value = journal,
                onValueChange = { journal = it },
                enabled = true,
                singleLine = false,
                maxLines = 10,
                label = { Text("Journal") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.End
            ){
                Button(
                    onClick = {},
                    enabled = true
                ) {
                    Text("Save")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EntryLogPreview(){
    DandelionTheme {
        LogEntryScreen()
    }
}
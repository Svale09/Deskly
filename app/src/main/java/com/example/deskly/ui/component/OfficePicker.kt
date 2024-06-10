package com.example.deskly.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.deskly.Models.Office
import com.example.deskly.ViewModels.ReserveDeskViewModel

@Composable
fun OfficePicker(
    offices: List<Office>,
    viewModel: ReserveDeskViewModel,
    selectedOfficeId: String,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = offices.find { it.id == selectedOfficeId }?.name ?: "Select an office",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true }
                .padding(8.dp)
                .background(Color.Gray)
                .padding(16.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            offices.forEach { office ->
                DropdownMenuItem(
                    text = { Text(office.name) },
                    onClick = { viewModel.setOffice(office.id); expanded = false })
            }
        }
    }
}
package com.example.deskly.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deskly.Models.Office
import com.example.deskly.Models.mockOffices

@Composable
fun OfficePicker(
    offices: List<Office>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOfficeId by remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
            .background(Color.Transparent)
            .clickable { expanded = !expanded }
            .padding(10.dp)
            .width(175.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = (if (selectedOfficeId.isEmpty()) {
                "Select Office"
            } else {
                offices.find { it.id == selectedOfficeId }?.name ?: ""
            }),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Black
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            offices.forEach { office ->
                DropdownMenuItem(
                    text = { Text(text = office.name) },
                    onClick = {
                        selectedOfficeId = office.id
                        expanded = false
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun OfficePickerPreview() {
    OfficePicker(offices = mockOffices)
}

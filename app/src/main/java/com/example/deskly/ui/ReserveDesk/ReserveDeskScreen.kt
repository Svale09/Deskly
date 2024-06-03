package com.example.deskly.ui.ReserveDesk

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.deskly.ui.component.CustomAppBar
import com.example.deskly.ui.component.CustomInputField

@Composable
fun ReserveDeskScreen(
    onDeskSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var office: String = ""

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Reserve Desk",
                modifier = modifier
            )
        }
    ) { padding ->
        Column {
            Row() {
                //TODO add the dropdown menu and the date picker.
            }
        }
    }
}
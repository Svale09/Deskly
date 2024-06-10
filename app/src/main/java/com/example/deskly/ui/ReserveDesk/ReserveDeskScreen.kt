package com.example.deskly.ui.ReserveDesk

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.deskly.Models.Office
import com.example.deskly.ViewModels.ReserveDeskViewModel
import com.example.deskly.ui.component.CustomAppBar
import java.time.LocalDateTime
import java.time.ZoneId
import androidx.compose.material3.DisplayMode


@Composable
fun ReserveDeskScreen(
    onDeskSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogOutClick: () -> Unit
) {
    var office: String = ""

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Reserve Desk",
                modifier = modifier,
                onLogOutClick = onLogOutClick
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

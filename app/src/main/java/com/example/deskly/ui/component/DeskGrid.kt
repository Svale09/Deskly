package com.example.deskly.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deskly.Models.Desk
import com.example.deskly.Models.mockDesks
import com.example.deskly.ViewModels.ReserveDeskViewModel

@Composable
fun DeskGrid(
    desks: List<Desk>,
    date: String,
    modifier: Modifier = Modifier,
    viewModel: ReserveDeskViewModel
) {
    val selectedDeskId by viewModel.selectedDeskId.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(desks.size) { index ->
            val desk = desks[index]
            val isReserved = desk.reservedDates.contains(date)
            val isSelected = desk.id == selectedDeskId
            DeskItem(
                isReserved = isReserved,
                isSelected = isSelected,
                onClick = { viewModel.selectDesk(desk.id) }
            )
        }
    }
}

@Preview
@Composable
private fun DeskGridPreview() {
    DeskGrid(desks = mockDesks, date = "14/06/2024", modifier = Modifier, ReserveDeskViewModel())
}

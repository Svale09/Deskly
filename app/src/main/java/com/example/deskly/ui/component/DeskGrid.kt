package com.example.deskly.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deskly.Models.Desk
import com.example.deskly.Models.mockDesks

@Composable
fun DeskGrid(
    desks: List<Desk>,
    date: String,
    modifier: Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        items(desks.count()) { desk ->
            val isReserved = desks[desk].reservedDates.contains(date)
            DeskItem(isReserved = isReserved)
        }
    }
}

@Preview
@Composable
private fun DeskGridPreview() {
    DeskGrid(desks = mockDesks, date = "14/06/2024", modifier = Modifier)

}
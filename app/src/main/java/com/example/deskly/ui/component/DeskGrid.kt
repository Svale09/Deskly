package com.example.deskly.ui.component

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.example.deskly.Models.Desk

@Composable
fun DeskGrid(
    desks: List<Desk>
) {
    LazyVerticalGrid(columns = GridCells.Fixed(4)) {

    }
}
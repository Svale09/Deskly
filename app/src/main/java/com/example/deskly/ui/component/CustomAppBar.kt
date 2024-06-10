package com.example.deskly.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.deskly.ui.theme.jomhuriaFontFamily
import com.example.deskly.ui.theme.primaryBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(
    title: String,
    modifier: Modifier = Modifier,
    onLogOutClick: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = primaryBlue
        ),
        title = {
            Text(
                text = title,
                fontFamily = jomhuriaFontFamily,
                fontSize = 60.sp,
                color = Color.White
            )
        },
        //actions =
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CustomAppBarPreview() {
    CustomAppBar(title = "Reserve a desk")
}
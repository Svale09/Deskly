package com.example.deskly.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deskly.R
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
        actions = {
            IconButton(onClick = onLogOutClick) {
                Icon(
                    painter = painterResource(id = R.drawable.logout_svgrepo_com),
                    contentDescription = "Log out button",
                    modifier = Modifier
                        .height(20.dp)
                        .width(20.dp),
                    tint = Color.White
                )
            }
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun CustomAppBarPreview() {
    CustomAppBar(title = "Reserve a desk")
}
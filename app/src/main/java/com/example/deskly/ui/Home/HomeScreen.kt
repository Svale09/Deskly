package com.example.deskly.ui.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.theme.jomhuriaFontFamily

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Deskly",
            fontFamily = jomhuriaFontFamily,
            fontSize = 80.sp
        )
        CustomButton(
            text = "Log In",
            onClick = { /*TODO*/ },
            modifier.padding(bottom = 10.dp))
        CustomButton(text = "Sign Up", onClick = { /*TODO*/ })
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    //HomeScreen()
}
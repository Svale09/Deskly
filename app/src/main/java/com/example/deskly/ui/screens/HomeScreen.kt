package com.example.deskly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.theme.jomhuriaFontFamily

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Scaffold { padding ->
        Column(
            modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Deskly",
                fontFamily = jomhuriaFontFamily,
                fontSize = 80.sp,
                color = Color.DarkGray
            )
            CustomButton(
                text = "Log In",
                onClick = { navController.navigate("log_in") },
                modifier.padding(bottom = 10.dp)
            )
            CustomButton(text = "Sign Up", onClick = { navController.navigate("sign_up") })
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val mockNavController = rememberNavController()
    HomeScreen(navController = mockNavController)
}
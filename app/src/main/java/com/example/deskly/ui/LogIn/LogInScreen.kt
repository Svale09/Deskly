package com.example.deskly.ui.LogIn

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
import com.example.deskly.ui.component.CustomInputField
import com.example.deskly.ui.theme.jomhuriaFontFamily

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var inputEmail: String = ""
        var inputPassword: String = ""
        Text(
            text = "Log In",
            fontFamily = jomhuriaFontFamily,
            fontSize = 60.sp,
        )
        CustomInputField(
            label = "Email",
            placeholder = "Enter your email",
            value = inputEmail,
            onValueChange = { newEmail -> inputEmail = newEmail },
        )

        CustomInputField(
            label = "Password",
            placeholder = "Input your password",
            value = inputPassword,
            onValueChange = { newPassword -> inputPassword = newPassword }
        )
    }
}

@Preview
@Composable
private fun LoginPagePreview() {
    //LoginScreen(navController = NavController())
}
package com.example.deskly.ui.LogIn

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deskly.ViewModels.AuthenticationViewModel
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.CustomInputField
import com.example.deskly.ui.theme.jomhuriaFontFamily

@Composable
fun LoginScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
) {
    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }

    val loginState by authenticationViewModel.loginState.observeAsState()

    // Navigate to reserve_desk screen if login is successful
    loginState?.let { user ->
        if (user != null) {
            LaunchedEffect(Unit) {
                navController.navigate("reserve_desk") {
                    popUpTo("log_in") { inclusive = true }
                }
            }
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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

            CustomButton(
                text = "Log In",
                onClick = {
                    authenticationViewModel.logIn(
                        email = inputEmail,
                        password = inputPassword
                    )
                },
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun LoginPagePreview() {
    // LoginScreen(navController = NavController())
}

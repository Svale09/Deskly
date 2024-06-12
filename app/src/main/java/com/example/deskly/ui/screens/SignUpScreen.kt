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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deskly.viewModels.AuthenticationViewModel
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.CustomInputField
import com.example.deskly.ui.theme.jomhuriaFontFamily

@Composable
fun SignUpScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
) {
    var inputEmail: String = ""
    var inputPassword: String = ""

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp)
                .padding(padding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                fontFamily = jomhuriaFontFamily,
                fontSize = 60.sp,
            )
            CustomInputField(
                label = "Email",
                placeholder = "Enter your email",
                value = inputEmail,
                onValueChange = { newEmail -> inputEmail = newEmail },
                isPasswordField = false
            )

            CustomInputField(
                label = "Password",
                placeholder = "Input your password",
                value = inputPassword,
                onValueChange = { newPassword -> inputPassword = newPassword },
                isPasswordField = true,
                modifier = Modifier.padding(top = 20.dp)
            )

            CustomButton(
                text = "Sign Up",
                onClick = {
                    authenticationViewModel.signUp(
                        email = inputEmail,
                        password = inputPassword
                    );
                    navController.navigate("reserve_desk")
                },
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}
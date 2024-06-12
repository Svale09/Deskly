package com.example.deskly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deskly.Utilities.SharedPrefsManager
import com.example.deskly.ViewModels.AuthenticationViewModel
import com.example.deskly.ui.Home.HomeScreen
import com.example.deskly.ui.LogIn.LoginScreen
import com.example.deskly.ui.ReserveDesk.ReserveDeskScreen
import com.example.deskly.ui.SignUp.SignUpScreen
import com.example.deskly.ui.theme.DesklyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesklyTheme {
                val sharedPrefsManager = SharedPrefsManager.getInstance(this)

                val navController = rememberNavController()
                val authenticationViewModel = remember { AuthenticationViewModel(this) }


                LaunchedEffect(Unit) {
                    if (authenticationViewModel.currentUser != null) {
                        navController.navigate("reserve_desk") {
                            popUpTo("home_screen") { inclusive = true }
                        }
                    }
                }

                NavHost(navController = navController, startDestination = "home_screen") {
                    composable("home_screen") {
                        HomeScreen(navController = navController)
                    }
                    composable("log_in") {
                        LoginScreen(
                            navController = navController,
                            authenticationViewModel = authenticationViewModel
                        )
                    }
                    composable(route = "sign_up") {
                        SignUpScreen(
                            navController = navController,
                            authenticationViewModel = authenticationViewModel
                        )
                    }
                    composable(route = "reserve_desk") {
                        ReserveDeskScreen(
                            navController = navController,
                            onDeskSelected = { /*TODO reserve desk*/ },
                            onLogOutClick = {
                                authenticationViewModel.logOut()
                                navController.navigate("home_screen")
                            },
                            userRole = sharedPrefsManager.getUserRole()
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        DesklyTheme {
            Greeting("Android")
        }
    }
}

package com.example.deskly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deskly.ui.Home.HomeScreen
import com.example.deskly.ui.LogIn.LoginScreen
import com.example.deskly.ui.theme.DesklyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesklyTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home_screen") {
                    composable("home_screen") {
                        HomeScreen(navController = navController)
                    }
                    composable("log_in") {
                        LoginScreen(navController = navController)
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
package com.example.deskly.ui.ReserveDesk


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deskly.Models.mockOffices
import com.example.deskly.ui.component.CustomAppBar
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.DatePicker
import com.example.deskly.ui.component.OfficePicker


@Composable
fun ReserveDeskScreen(
    onDeskSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogOutClick: () -> Unit
) {
    var office: String = ""

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Reserve Desk",
                modifier = modifier,
                onLogOutClick = onLogOutClick
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        OfficePicker(offices = mockOffices)
                        DatePicker()
                    }
                    //TODO create a function for displaying the desk grid
                }
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(
                    text = "Reserve a desk",
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .padding(bottom = 30.dp)
                )
            }
        }
    }
}

@Composable
fun DeskGrid(){
    //TODO
}


@Preview
@Composable
private fun PreviewReserveDeskScreen() {
    val mockNavController = rememberNavController()
    ReserveDeskScreen(
        onDeskSelected = {},
        onLogOutClick = {},
        navController = mockNavController
    )
}


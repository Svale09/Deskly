package com.example.deskly.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deskly.R
import com.example.deskly.ViewModels.ReserveDeskViewModel
import com.example.deskly.ui.component.CustomAppBar
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.DatePicker
import com.example.deskly.ui.component.DeskGrid
import com.example.deskly.ui.component.OfficePicker

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val route: String
)

@Composable
fun ReserveDeskScreen(
    onDeskSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogOutClick: () -> Unit,
    userRole: Int?,
    viewModel: ReserveDeskViewModel
) {
    val offices by viewModel.offices.collectAsState()
    val desks by viewModel.desks.collectAsState()

    var selectedDate by rememberSaveable { mutableStateOf("") }
    var selectedOffice by rememberSaveable { mutableStateOf("") }
    var selectedDesk by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.desks.collect {
            Log.d("desks", it.toString())
        }
    }

    val items = listOf(
        BottomNavigationItem(
            title = "Reserve Desk",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.calendar_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.calendar),
            route = "reserve_desk"
        ),
        BottomNavigationItem(
            title = "Add Office",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.edit_selected),
            unselectedIcon = ImageVector.vectorResource(id = R.drawable.edit_unselected),
            route = "add_office"
        )
    )

    var selectetItemIndex by rememberSaveable { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Reserve Desk",
                modifier = modifier,
                onLogOutClick = onLogOutClick
            )
        },
        bottomBar = {
            if (userRole == 0) {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            label = { Text(text = item.title) },
                            selected = selectetItemIndex == index,
                            onClick = {
                                selectetItemIndex = index;
                                navController.navigate(item.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectetItemIndex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            })
                    }
                }
            }
        }

    ) { padding ->
        Box(
            modifier = Modifier
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
                        OfficePicker(offices = offices, {
                            viewModel.loadDesksForOffice(it)
                            viewModel.clearSelectedDesk()
                            selectedOffice = it
                        })
                        DatePicker(onDateSelected = {
                            selectedDate = it
                            viewModel.clearSelectedDesk()
                        })
                    }
                    DeskGrid(
                        desks = desks,
                        date = selectedDate,
                        modifier = Modifier.padding(vertical = 40.dp),
                        viewModel = viewModel
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                CustomButton(
                    text = "Reserve a desk",
                    onClick = {
                        viewModel.selectedDeskId.value?.let {
                            viewModel.reserveDesk(
                                deskId = it,
                                date = selectedDate,
                                officeName = selectedOffice
                            )
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                        .padding(bottom = 20.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewReserveDeskScreen() {
    val mockNavController = rememberNavController()
    ReserveDeskScreen(
        onDeskSelected = {},
        onLogOutClick = {},
        navController = mockNavController,
        userRole = 0,
        viewModel = ReserveDeskViewModel()
    )
}

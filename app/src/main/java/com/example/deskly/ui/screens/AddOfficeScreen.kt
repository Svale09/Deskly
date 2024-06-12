package com.example.deskly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.deskly.ui.component.CustomAppBar
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.CustomInputField


@Composable
fun AddOfficeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogOutClick: () -> Unit,
) {
    var inputOfficeName by remember { mutableStateOf("") }

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

    var selectetItemIndex by rememberSaveable { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
            CustomAppBar(
                title = "Add Office",
                modifier = modifier,
                onLogOutClick = onLogOutClick
            )
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        label = { Text(text = item.title) },
                        selected = selectetItemIndex == index,
                        onClick = {
                            selectetItemIndex = index
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
                    CustomInputField(
                        label = "Office name",
                        placeholder = "Input the office name",
                        value = "",
                        onValueChange = { newOfficeName -> inputOfficeName = newOfficeName }
                    )
                    //TODO create a function for displaying the desk grid
                }
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Row {
                        IconButton(onClick = { /*TODO */ }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.add_desk),
                                contentDescription = "Add desk"
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.remove_desk),
                                contentDescription = "Remove desk"
                            )
                        }
                    }
                    CustomButton(
                        text = "Save office",
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
}


@Preview
@Composable
private fun PreviewAddOfficeScreen() {
    val mockNavController = rememberNavController()
    AddOfficeScreen(
        navController = mockNavController,
        onLogOutClick = {}
    )
}


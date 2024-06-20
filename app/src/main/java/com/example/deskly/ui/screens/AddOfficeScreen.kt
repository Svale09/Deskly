package com.example.deskly.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.deskly.Models.Desk
import com.example.deskly.R
import com.example.deskly.ViewModels.AddOfficeViewModel
import com.example.deskly.ui.component.CustomAppBar
import com.example.deskly.ui.component.CustomButton
import com.example.deskly.ui.component.CustomInputField
import com.example.deskly.ui.component.DeskItem
import com.example.deskly.ui.component.IconAndLabelButton
import com.example.deskly.ui.theme.primaryBlue
import com.example.deskly.ui.theme.primaryRed


@Composable
fun AddOfficeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    onLogOutClick: () -> Unit,
    addOfficeViewModel: AddOfficeViewModel
) {
    var inputOfficeName by rememberSaveable { mutableStateOf("") }
    val desks by addOfficeViewModel.desks.collectAsState()


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
                        value = inputOfficeName,
                        onValueChange = { newOfficeName -> inputOfficeName = newOfficeName },
                        errorState = false,
                    )
                    LazyVerticalGrid(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .height(370.dp),
                        columns = GridCells.Fixed(4),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp),
                    ) {
                        items(desks.size) {
                            DeskItem(isReserved = false, isSelected = false, onClick = {})
                        }
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 75.dp)
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        IconAndLabelButton(
                            title = "Add desk",
                            vector = ImageVector.vectorResource(id = R.drawable.add_desk),
                            onClick = {
                                addOfficeViewModel.addDesk(
                                    Desk(
                                        id = desks.size + 1,
                                        officeId = inputOfficeName,
                                        reservedDates = emptyList()
                                    )
                                )
                            },
                            color = primaryBlue
                        )
                        IconAndLabelButton(
                            title = "Remove desk",
                            vector = ImageVector.vectorResource(id = R.drawable.remove_desk),
                            onClick = {
                                addOfficeViewModel.removeDesk()
                            },
                            color = primaryRed
                        )
                    }
                    CustomButton(
                        text = "Save office",
                        onClick = {
                            addOfficeViewModel.addOffice(inputOfficeName);
                            inputOfficeName = ""
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
}


@Preview
@Composable
private fun PreviewAddOfficeScreen() {
    val mockNavController = rememberNavController()
    AddOfficeScreen(
        navController = mockNavController,
        onLogOutClick = {},
        addOfficeViewModel = AddOfficeViewModel()
    )
}


package com.goen.home.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.goen.home.NavigationItem
import com.goen.home.ui.comp.IdeaCreateView
import com.goen.home.view_model.HomeViewModel
import com.goen.utils.compose.DaikuAppTheme

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeCompose(
    tab: @Composable() (navController2: NavHostController, innerPadding: PaddingValues)->Unit,

) {

    val navController = rememberNavController()

    val homeViewModel: HomeViewModel = hiltViewModel()

    DaikuAppTheme {
        Scaffold(
            drawerGesturesEnabled = false,
            bottomBar = {
                BottomBar(
                    navController = navController
                )
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    homeViewModel.openIdeaCreateAlert()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = null
                    )
                }
            }
        ) {
            tab(navController2 = navController, innerPadding = it)
        }

        IdeaCreateAlert(viewModel = homeViewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun IdeaCreateAlert(viewModel: HomeViewModel) {

    val saveIdea: () -> Unit = {
        viewModel.createIdea()
    }

    if(viewModel.ideaCreateAlert.value) {
        Dialog(
            onDismissRequest = {
                viewModel.closeIdeaCreateAlert()
            },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                IdeaCreateView(viewModel = viewModel, save = saveIdea)
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
) {

    var items = listOf (
        NavigationItem.Home,
        NavigationItem.Favorite,
        NavigationItem.Profile
    )


    BottomNavigation(
        elevation = 10.dp,
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    ) {
        val navBackStateEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStateEntry?.destination?.route

        items.forEach {item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.title
                    )
                },
                label = { Text(text = item.title) },
                selectedContentColor = DaikuAppTheme.colors.topAppBarTitle,
                unselectedContentColor = DaikuAppTheme.colors.topAppBarTitle.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
            )
        }
    }
}

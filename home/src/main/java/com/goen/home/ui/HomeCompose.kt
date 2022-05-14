package com.goen.home.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.goen.home.NavigationItem
import com.goen.home.view_model.HomeIndexViewModel
import com.goen.utils.compose.DaikuAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun HomeCompose(
    tab: @Composable() (navController2: NavHostController, innerPadding: PaddingValues)->Unit,

) {

    val navController = rememberNavController()
    DaikuAppTheme {
        Scaffold(
            drawerGesturesEnabled = false,
            bottomBar = {
                BottomBar(
                    navController = navController
                )
            }
        ) {
            tab(navController2 = navController, innerPadding = it)
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

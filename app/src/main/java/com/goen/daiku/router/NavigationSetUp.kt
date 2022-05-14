package com.goen.daiku.router

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.goen.daiku.router.account.accountDetailNav
import com.goen.daiku.router.goal.goalNav
import com.goen.daiku.router.home.homeNav
import com.goen.daiku.router.nav.*
import com.goen.daiku.router.process.processNav
import com.goen.daiku.router.processHistory.processHistoryNav
import com.goen.domain.entity.Account
import com.goen.goal.ui.goalDetailMainCompose

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun NavigationSetUp(
    navController: NavHostController,
    account: Account
) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        accountDetailNav(
            accountAction = AccountNavAction(navController),
            navController = navController,
            goalAction = GoalNavAction(navController)
        )

        homeNav(
            action = AccountNavAction(navController),
            navController = navController,
            homeNavAction = HomeNavAction(navController),
            goalNavAction = GoalNavAction(navController),
            account = account
        )

        goalNav(
            navController = navController,
            action = GoalNavAction(navController)
        )

        processNav(
            navController = navController,
            action = ProcessNavAction(navController),
            actionHistory = ProcessHistoryNavAction(navController = navController)
        )

        processHistoryNav(
            navController = navController,
            action = ProcessHistoryNavAction(navController)
        )


    }
}
package com.goen.daiku.router

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.goen.daiku.router.account.accountDetailNav
import com.goen.daiku.router.goal.goalNav
import com.goen.daiku.router.home.homeNav
import com.goen.daiku.router.nav.*
import com.goen.daiku.router.process.processNav
import com.goen.daiku.router.processHistory.processHistoryNav
import com.goen.daiku.router.story.storyNav
import com.goen.domain.model.entity.Account

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
            navController = navController,
            goalAction = GoalNavAction(navController),
            accountAction = AccountNavAction(navController),
            storyAction = StoryNavAction(navController)
        )

        homeNav(
            action = AccountNavAction(navController),
            navController = navController,
            homeNavAction = HomeNavAction(navController),
            goalNavAction = GoalNavAction(navController),
            account = account
        )

        goalNav(
            navController = navController
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

        storyNav(
            navController = navController
        )

    }
}
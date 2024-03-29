package com.goen.daiku.router.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.goen.account.ui.AccountDetailMainCompose
import com.goen.daiku.router.nav.AccountNavAction
import com.goen.daiku.router.nav.GoalNavAction
import com.goen.daiku.router.nav.HomeNavAction
import com.goen.domain.model.entity.Account
import com.goen.favorite.ui.FavoriteMainPage
import com.goen.goal.ui.GoalArchiveDetailMainCompose
import com.goen.goal.ui.GoalCreateCompose
import com.goen.home.NavigationItem
import com.goen.home.ui.HomeCompose
import com.goen.home.ui.HomeMainCompose
import com.goen.maki.ui.MakiCreateMainCompose

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.homeNav(
    action: AccountNavAction,
    homeNavAction: HomeNavAction,
    goalNavAction: GoalNavAction,
    navController: NavHostController,
    account: Account
) {
    navigation(
        startDestination = "home_main",
        route = "home"
    ) {
        composable(
            "home_main"
        ) {
            HomeCompose(
                tab = {
                        navController2: NavHostController,
                        innerPadding: PaddingValues ->
                            BottomNavigation(
                                navController = navController2,
                                innerPadding = innerPadding,
                                action = action,
                                homeNavAction = homeNavAction,
                                account = account
                            )
                      }
            )
        }
        composable(
            "goal/create"
        ) {
            GoalCreateCompose(
                close = {navController.popBackStack()}
            )
        }
        composable(
            "maki/create"
        ) {
            MakiCreateMainCompose(
                close = {navController.popBackStack()}
            )
        }
        composable(
            "goal/archive/detail/{archiveId}/{archiveCreateDate}/{loginAccountId}",
            arguments = listOf(
                navArgument("archiveId") {
                    type = NavType.IntType
                },
                navArgument("archiveCreateDate") {
                    type = NavType.StringType
                },
                navArgument("loginAccountId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            var archiveId = args.arguments!!.getInt("archiveId")
            var loginAccountId = args.arguments!!.getInt("loginAccountId")
            var archiveCreateDate = args.arguments!!.getString("archiveCreateDate")!!

            GoalArchiveDetailMainCompose(
                archiveId = archiveId,
                archiveCreateDate = archiveCreateDate,
                navController = navController,
                gotoUpdateArchive = goalNavAction.gotoGoalArchiveUpdatePage,
                loginAccountId = loginAccountId
            )

        }
    }
}

@Composable
fun BottomNavigation(
    navController: NavHostController,
    homeNavAction: HomeNavAction,
    action: AccountNavAction,
    innerPadding: PaddingValues,
    account: Account
) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeMainCompose(
                createGoalPage = homeNavAction.createGoal,
                createMakiPage = homeNavAction.createMakiPage,
                innerPadding = innerPadding,
                gotoArchiveDetailPage = homeNavAction.gotoGoalArchiveDetail,
                account = account
            )
        }
        composable(NavigationItem.Favorite.route) {
            FavoriteMainPage(
                action.selectedGoal,
                innerPadding = innerPadding)
        }
        composable(NavigationItem.Profile.route) {
            AccountDetailMainCompose(
                selectGoalDetail = action.selectedGoal,
                selectGoalArchiveDetail = action.selectedGoalArchive,
                selectIdeaDetail = action.selectedIdea,
                selectMakiDetail = action.selectedMaki,
                gotoEditAccountInfo = action.updateAccount,
                innerPadding = innerPadding
            )
        }
    }
}
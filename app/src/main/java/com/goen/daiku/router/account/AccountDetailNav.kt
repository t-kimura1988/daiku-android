package com.goen.daiku.router.account

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.account.ui.AccountUpdateCompose
import com.goen.daiku.router.nav.GoalNavAction
import com.goen.goal.ui.GoalDetailMainCompose

fun NavGraphBuilder.accountDetailNav(
    goalAction: GoalNavAction,
    navController: NavHostController
) {
    navigation(
        startDestination = "account_detail",
        route = "account"
        ) {

        composable(
            "goal/detail/{goalId}/{createDate}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("createDate") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            GoalDetailMainCompose(
                goalId = backStackEntry.arguments!!.getInt("goalId"),
                createDate = backStackEntry.arguments!!.getString("createDate")!!,
                navController = navController,
                selectProcessDetail = goalAction.selectedProcess,
                gotoProcessCreate = goalAction.gotoProcessCreatePage,
                goalUpdatePage = goalAction.gotoGoalUpdatePage,
                goalArchivePage = goalAction.gotoGoalArchivePage,
                goalArchiveUpdatePage = goalAction.gotoGoalArchiveUpdatePage
            )

        }

        composable(
            "account/update"
        ) {

            AccountUpdateCompose(
                navController = navController
            )

        }
    }

}
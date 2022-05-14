package com.goen.daiku.router.goal

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.daiku.router.nav.GoalNavAction
import com.goen.goal.ui.GoalArchiveCreateCompose
import com.goen.goal.ui.GoalArchiveUpdateCompose
import com.goen.goal.ui.GoalUpdateCompose
import com.goen.process.ui.processCreateCompose

@OptIn(ExperimentalComposeUiApi::class)
fun NavGraphBuilder.goalNav(
    action: GoalNavAction,
    navController: NavHostController
) {
    navigation(
        startDestination = "goal",
        route = "goal_nav"
    ) {
        composable(
            "process/create/{goalId}/{goalCreateDate}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("goalCreateDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            processCreateCompose(
                goalId = args.arguments!!.getInt("goalId"),
                goalCreateDate = args.arguments!!.getString("goalCreateDate")!!,
                navController = navController)
        }
        composable(
            "goal/update/{goalId}/{createDate}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("createDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            GoalUpdateCompose (
                goalId = args.arguments!!.getInt("goalId"),
                createDate = args.arguments!!.getString("createDate")!!,
                navController = navController
            )
        }
        composable(
            "goal/archive/{goalId}/{createDate}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("createDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            GoalArchiveCreateCompose (
                goalId = args.arguments!!.getInt("goalId"),
                createDate = args.arguments!!.getString("createDate")!!,
                navController = navController
            )
        }
        composable(
            "goal/update/archive/{archiveId}/{archiveCreateDate}",
            arguments = listOf(
                navArgument("archiveId") {
                    type = NavType.IntType
                },
                navArgument("archiveCreateDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            GoalArchiveUpdateCompose (
                archiveId = args.arguments!!.getInt("archiveId"),
                archiveCreateDate = args.arguments!!.getString("archiveCreateDate")!!,
                navController = navController
            )
        }
    }

}
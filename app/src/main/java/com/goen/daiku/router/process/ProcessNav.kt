package com.goen.daiku.router.process

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.daiku.router.nav.ProcessHistoryNavAction
import com.goen.daiku.router.nav.ProcessNavAction
import com.goen.process.ui.ProcessDetailPage
import com.goen.process.ui.ProcessUpdateCompose

fun NavGraphBuilder.processNav(
    action: ProcessNavAction,
    actionHistory: ProcessHistoryNavAction,
    navController: NavHostController
) {
    navigation(
        startDestination = "process",
        route = "process_nav"
        ) {
        composable(
            "process/detail/{processId}/{goalId}/{goalCreateDate}",
            arguments = listOf(
                navArgument("processId") {
                    type = NavType.IntType
                },
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("goalCreateDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            ProcessDetailPage(
                processId = args.arguments!!.getInt("processId"),
                goalCreateDate = args.arguments!!.getString("goalCreateDate")!!,
                navController = navController,
                gotoProcessHistoryCreate = actionHistory.gotoProcessHistoryCreatePage,
                updateComment = actionHistory.updateComment,
                processUpdate = action.gotoProcessUpdatePage,
                goalId = args.arguments!!.getInt("goalId"),
                processStatusUpdate = actionHistory.updateStatusPage
            )
        }

        composable(
            "process/update/{processId}/{goalId}/{goalCreateDate}",
            arguments = listOf(
                navArgument("processId") {
                    type = NavType.IntType
                },
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("goalCreateDate") {
                    type = NavType.StringType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            ProcessUpdateCompose(
                goalId = args.arguments!!.getInt("goalId"),
                processId = args.arguments!!.getInt("processId"),
                goalCreateDate = args.arguments!!.getString("goalCreateDate")!!,
                navController = navController
            )
        }
    }

}
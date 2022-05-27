package com.goen.daiku.router.processHistory

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.daiku.router.nav.ProcessHistoryNavAction
import com.goen.processhistory.param.ProcessHistoryCreateDisplayParam
import com.goen.processhistory.param.ProcessHistoryUpdateCommentDisplayParam
import com.goen.processhistory.param.ProcessHistoryUpdateStatusDisplayParam
import com.goen.processhistory.ui.ProcessHistoryCreatePage
import com.goen.processhistory.ui.ProcessHistoryUpdateCommentPage
import com.goen.processhistory.ui.ProcessHistoryUpdateStatusPage

fun NavGraphBuilder.processHistoryNav(
    action: ProcessHistoryNavAction,
    navController: NavHostController
) {
    navigation(
        startDestination = "process-history",
        route = "process-history_nav"
        ) {
        composable(
            "process-history/create/{processId}/{status}/{priority}",
            arguments = listOf(
                navArgument("processId") {
                    type = NavType.IntType
                },
                navArgument("status") {
                    type = androidx.navigation.NavType.IntType
                },
                navArgument("priority") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            ProcessHistoryCreatePage(
                input = ProcessHistoryCreateDisplayParam(
                    processId = args.arguments!!.getInt("processId"),
                    status = args.arguments!!.getInt("status"),
                    priority = args.arguments!!.getInt("priority")
                ),
                navHostController = navController
            )
        }

        composable(
            "process-history/update/comment/{processHistoryId}/{goalCreateDate}",
            arguments = listOf(
                navArgument("processHistoryId") {
                    type = NavType.IntType
                },
                navArgument("goalCreateDate") {
                    type = NavType.StringType
                },
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            ProcessHistoryUpdateCommentPage(
                input = ProcessHistoryUpdateCommentDisplayParam(
                    processHistoryId = args.arguments!!.getInt("processHistoryId"),
                    goalCreateDate = args.arguments!!.getString("goalCreateDate")!!
                ),
                close = action.close
            )
        }

        composable(
            "process-history/update/status/{processId}/{status}/{priority}",
            arguments = listOf(
                navArgument("processId") {
                    type = NavType.IntType
                },
                navArgument("status") {
                    type = NavType.IntType
                },
                navArgument("priority") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            ProcessHistoryUpdateStatusPage(
                input = ProcessHistoryUpdateStatusDisplayParam(
                    processId = args.arguments!!.getInt("processId"),
                    status = args.arguments!!.getInt("status"),
                    priority = args.arguments!!.getInt("priority")
                ),
                navHostController = navController
            )
        }
    }

}
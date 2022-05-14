package com.goen.daiku.router.processHistory

import android.util.Log
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.daiku.router.nav.ProcessHistoryNavAction
import com.goen.daiku.router.nav.ProcessNavAction
import com.goen.process.ui.ProcessDetailPage
import com.goen.processhistory.param.ProcessHistoryCreateDisplayParam
import com.goen.processhistory.param.ProcessHistoryUpdateCommentDisplayParam
import com.goen.processhistory.ui.ProcessHistoryCreatePage
import com.goen.processhistory.ui.ProcessHistoryUpdateCommentPage

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
            Log.println(Log.INFO, "Router", "process-history create compose router")
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
            Log.println(Log.INFO, "Router", "process-history update comment router")
            var args = requireNotNull(backStackEntry)
            ProcessHistoryUpdateCommentPage(
                input = ProcessHistoryUpdateCommentDisplayParam(
                    processHistoryId = args.arguments!!.getInt("processHistoryId"),
                    goalCreateDate = args.arguments!!.getString("goalCreateDate")!!
                ),
                close = action.close
            )
        }
    }

}
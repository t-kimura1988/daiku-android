package com.goen.daiku.router.nav

import androidx.navigation.NavHostController

class ProcessHistoryNavAction(
    navController: NavHostController
) {
    val gotoProcessHistoryCreatePage: (Int, Int, Int) -> Unit = {processId: Int, status: Int, priority: Int ->
        navController.navigate("process-history/create/$processId/$status/$priority")
    }
    val updateComment: (Int, String) -> Unit = {processHistoryId: Int, goalCreateDate: String ->
        navController.navigate("process-history/update/comment/$processHistoryId/$goalCreateDate")
    }

    val save: (Int) -> Unit = {processId: Int ->
        navController.navigate("process-history/create/$processId")
    }

    val updateStatusPage: (Int, Int, Int) -> Unit = {processId: Int, status: Int, priority: Int ->
        navController.navigate("process-history/update/status/$processId/$status/$priority")
    }

    val close: () -> Unit = {navController.popBackStack()}
}
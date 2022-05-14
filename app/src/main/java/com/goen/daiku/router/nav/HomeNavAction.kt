package com.goen.daiku.router.nav

import android.util.Log
import androidx.navigation.NavHostController

class HomeNavAction(
    navController: NavHostController
) {

    val createGoal: () -> Unit = {
        navController.navigate("goal/create")
    }

    val gotoGoalArchiveDetail: (Int, String, Int) -> Unit = {archiveId: Int, archiveCreateDate: String, accountId: Int ->
        navController.navigate("goal/archive/detail/${archiveId}/${archiveCreateDate}/${accountId}")
    }
}
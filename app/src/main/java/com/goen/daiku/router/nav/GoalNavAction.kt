package com.goen.daiku.router.nav

import androidx.navigation.NavHostController

class GoalNavAction(
    navController: NavHostController
) {
    val selectedProcess: (Int, Int, String) -> Unit = { processId: Int, goalId: Int, goalCreateDate: String ->
        navController.navigate("process/detail/$processId/$goalId/$goalCreateDate")
    }

    val gotoProcessCreatePage: (Int, String) -> Unit = {goalId: Int, goalCreateDate: String ->
        navController.navigate("process/create/$goalId/$goalCreateDate")
    }

    val gotoGoalUpdatePage: (Int, String) -> Unit = {goalId: Int, createDate: String ->
        navController.navigate("goal/update/$goalId/$createDate")
    }

    val gotoGoalArchivePage: (Int, String) -> Unit = {goalId: Int, createDate: String ->
        navController.navigate("goal/archive/$goalId/$createDate")
    }

    val gotoGoalArchiveUpdatePage: (Int, String) -> Unit = {archiveId: Int, archiveCcreateDate: String ->
        navController.navigate("goal/update/archive/$archiveId/$archiveCcreateDate")
    }
}
package com.goen.daiku.router.nav

import androidx.navigation.NavHostController

class ProcessNavAction(
    navController: NavHostController
) {

    val gotoProcessUpdatePage: (Int, Int, String) -> Unit = {processId: Int, goalId: Int, goalCreateDate: String ->
        navController.navigate("process/update/$processId/$goalId/$goalCreateDate")
    }
}
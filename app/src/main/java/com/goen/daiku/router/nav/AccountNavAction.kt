package com.goen.daiku.router.nav

import android.util.Log
import androidx.navigation.NavHostController

class AccountNavAction(
    navController: NavHostController
) {
    val selectedGoal: (Int, String) -> Unit = { goalId: Int, createDate: String ->
        navController.navigate("goal/detail/$goalId/$createDate")
    }

    val updateAccount: () -> Unit = {
        navController.navigate("account/update")
    }
}
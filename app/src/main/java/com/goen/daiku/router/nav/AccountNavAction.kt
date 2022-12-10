package com.goen.daiku.router.nav

import androidx.navigation.NavHostController

class AccountNavAction(
    navController: NavHostController
) {
    val selectedGoal: (Int, String) -> Unit = { goalId: Int, createDate: String ->
        navController.navigate("goal/detail/$goalId/$createDate")
    }
    val selectedGoalArchive: (Int, String, Int) -> Unit = { archiveId: Int, archiveCreateDate: String, loginId: Int ->
        navController.navigate("goal/archive/detail/${archiveId}/${archiveCreateDate}/${loginId}")
    }
    val selectedIdea: (Int, Int) -> Unit = { storyId: Int, ideaId: Int ->
        navController.navigate("story/detail/${ideaId}/$storyId")
    }
    val selectedMaki: (Int) -> Unit = { makiId: Int ->
        navController.navigate("maki/detail/${makiId}")
    }

    val updateAccount: () -> Unit = {
        navController.navigate("account/update")
    }
}
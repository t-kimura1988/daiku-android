package com.goen.daiku.router.nav

import androidx.navigation.NavHostController

class StoryNavAction(
    navController: NavHostController
) {
    val gotoStoryCharacterCreatePage: (Int, Int) -> Unit = { idea: Int, story: Int ->
        navController.navigate("story/character/create/$idea/$story")
    }
    val gotoStoryBodyUpdatePage: (Int, Int) -> Unit = { idea: Int, story: Int ->
        navController.navigate("story/update-story-body/$idea/$story")
    }

}
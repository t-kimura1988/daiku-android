package com.goen.daiku.router.story

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.story.ui.StoryBodyUpdateCompose
import com.goen.story.ui.StoryCharacterCreateCompose

fun NavGraphBuilder.storyNav(
    navController: NavHostController
) {
    navigation(
        startDestination = "story",
        route = "story_nav"
    ) {
        composable(
            "story/character/create/{ideaId}/{storyId}",
            arguments = listOf(
                navArgument("ideaId") {
                    type = NavType.IntType
                },
                navArgument("storyId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            StoryCharacterCreateCompose(
                ideaId = args.arguments!!.getInt("ideaId"),
                storyId = args.arguments!!.getInt("storyId"),
                navController = navController)
        }
        composable(
            "story/update-story-body/{ideaId}/{storyId}",
            arguments = listOf(
                navArgument("ideaId") {
                    type = NavType.IntType
                },
                navArgument("storyId") {
                    type = NavType.IntType
                }
            )
        ) {backStackEntry ->
            var args = requireNotNull(backStackEntry)
            StoryBodyUpdateCompose(
                ideaId = args.arguments!!.getInt("ideaId"),
                storyId = args.arguments!!.getInt("storyId"),
                navController = navController)
        }
    }

}
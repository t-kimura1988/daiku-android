package com.goen.daiku.router.account

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.goen.account.ui.AccountUpdateCompose
import com.goen.daiku.router.nav.AccountNavAction
import com.goen.daiku.router.nav.GoalNavAction
import com.goen.daiku.router.nav.StoryNavAction
import com.goen.goal.ui.GoalDetailMainCompose
import com.goen.idea.ui.IdeaDetailMainCompose
import com.goen.maki.ui.MakiDetailMainCompose

fun NavGraphBuilder.accountDetailNav(
    goalAction: GoalNavAction,
    navController: NavHostController,
    accountAction: AccountNavAction,
    storyAction: StoryNavAction
) {
    navigation(
        startDestination = "account_detail",
        route = "account"
        ) {

        composable(
            "goal/detail/{goalId}/{createDate}",
            arguments = listOf(
                navArgument("goalId") {
                    type = NavType.IntType
                },
                navArgument("createDate") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            GoalDetailMainCompose(
                goalId = backStackEntry.arguments!!.getInt("goalId"),
                createDate = backStackEntry.arguments!!.getString("createDate")!!,
                navController = navController,
                selectProcessDetail = goalAction.selectedProcess,
                gotoProcessCreate = goalAction.gotoProcessCreatePage,
                goalUpdatePage = goalAction.gotoGoalUpdatePage,
                goalArchivePage = goalAction.gotoGoalArchivePage,
                goalArchiveUpdatePage = goalAction.gotoGoalArchiveUpdatePage
            )

        }

        composable(
            "story/detail/{ideaId}/{storyId}",
            arguments = listOf(
                navArgument("storyId") {
                    type = NavType.IntType
                },
                navArgument("ideaId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            IdeaDetailMainCompose(
                storyId = backStackEntry.arguments!!.getInt("storyId"),
                ideaId = backStackEntry.arguments!!.getInt("ideaId"),
                navController = navController,
                gotoCharaCreate = storyAction.gotoStoryCharacterCreatePage,
                gotoStoryBodyUpdate = storyAction.gotoStoryBodyUpdatePage
            )

        }

        composable(
            "maki/detail/{makiId}",
            arguments = listOf(
                navArgument("makiId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            MakiDetailMainCompose(
                makiId = backStackEntry.arguments!!.getInt("makiId"),
                navController = navController,
                onClickItem = accountAction.selectedGoal
            )

        }

        composable(
            "account/update"
        ) {

            AccountUpdateCompose(
                navController = navController
            )

        }
    }

}
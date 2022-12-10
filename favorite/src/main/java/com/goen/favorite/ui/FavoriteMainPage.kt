package com.goen.favorite.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.domain.model.result.goal_favorite.GoalFavoriteSearchResult
import com.goen.favorite.R
import com.goen.favorite.view_model.FavoriteMainViewModel
import com.goen.utils.compose.DaikuAppTheme
import com.goen.utils.extentions.userImage

@Composable
fun FavoriteMainPage(
    selectGoalDetail: (goalId: Int, createDate: String) -> Unit,
    innerPadding: PaddingValues
) {
    val vm: FavoriteMainViewModel = hiltViewModel()
    LaunchedEffect(key1 = vm.favoriteGoalList, block = {
        vm.getFavoriteGoalList()
    })

    DaikuAppTheme {
        Scaffold(
            topBar = {
                FavoriteTopBar()
            }
        ) { padding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .padding(padding)
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),

                    ) {

                    items(vm.favoriteGoalList.value) {item ->
                        GoalFavoriteItem(
                            item = item,
                            onClickItem = selectGoalDetail
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun FavoriteTopBar() {

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.favorite_tab_name),
                color = DaikuAppTheme.colors.topAppBarTitle
            ) },
        actions = {
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}

@Composable
fun GoalFavoriteItem(
    item: GoalFavoriteSearchResult,
    onClickItem: (goalId: Int, createDate: String) -> Unit,
) {
    Column(
    modifier = Modifier
        .padding(8.dp)
        .clickable(onClick = { onClickItem(item.goalId, item.goalCreateDate) })
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)) {
            Text(
                text = item.title,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.purpose,
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                modifier = Modifier
                    .size(50.dp),
                painter = rememberImagePainter(
                    data = item.goalCreatedAccountImg.userImage(),
                    builder = {
                        placeholder(R.drawable.samurai)
                        transformations(CircleCropTransformation())
                    },
                ),
                contentDescription = item.goalCreatedAccountName
            )
            Text(
                text = item.goalCreatedAccountName,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
            )
        }
    }
}
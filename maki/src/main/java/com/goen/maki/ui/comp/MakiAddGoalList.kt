package com.goen.maki.ui.comp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.goen.domain.model.result.GoalSearchResult
import com.goen.maki.view_model.MakiDetailViewModel
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
internal fun MakiAddGoalList(
    viewModel: MakiDetailViewModel
) {

    LaunchedEffect(key1 = viewModel.makiAddGoalList, block = {
        load(viewModel = viewModel)
    })

    val checkMakiRelation: (Int, GoalSearchResult) -> Unit = { index: Int, item: GoalSearchResult ->
        System.out.println("tach tach tach")
        viewModel.changeCheck(index, item)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsWithImePadding()
        ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,

        ) {
        item {
            Text(text = "追加目標")
        }

        itemsIndexed(viewModel.makiAddGoalList.value) { index, item ->
            AddGoalItem(
                item = item,
                checkMakiRelation = { checkMakiRelation(index, item) },
                selected = if (viewModel.checkAddGoal.size == 0) false else viewModel.checkAddGoal[index].value.goalId != 0,
                isDark = isSystemInDarkTheme()
            )
            Divider(thickness = 3.dp)
        }

        item {
            Button(onClick = {
                viewModel.addMakiGoalList()
            }) {
                Text("追加")
            }
        }
    }
}

private fun load(viewModel: MakiDetailViewModel) {
    viewModel.getMakiAddGoalList()
}

@Composable
private fun AddGoalItem(
    item: GoalSearchResult,
    checkMakiRelation: () -> Unit,
    selected: Boolean,
    isDark: Boolean
) {
    fun backColor(): Color{
        return if (item.isExistMakiRelation()) {
            Color.Gray
        } else {
            if (isDark) Color.Black else Color.White
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backColor())
            .padding(8.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = selected,
            onCheckedChange = { checkMakiRelation() },
            enabled = !item.isExistMakiRelation()
        )

        Column() {
            if (item.isExistMakiRelation()) {
                Surface(
                    color = Color.Red,
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Red
                    ),
                    elevation = 8.dp,
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 8.dp)
                ) {
                    Text(
                        text = "巻に登録済み",
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .background(color = Color.Red)
                            .padding(start = 12.dp, end = 12.dp, top = 4.dp, bottom = 4.dp)
                    )
                }
            }
            Text(
                text =item.title,
                fontWeight = FontWeight.Bold
            )

        }
    }


}
package com.goen.idea.ui.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.goen.idea.view_model.IdeaDetailViewModel

@Composable
fun IdeaDetailStoryCompose(
    storyTitle: String,
    storyBody: String?,
    clickCharaCreate: () -> Unit,
    clickUpdateStoryBody: () -> Unit
) {
    val ideaDetailViewModel: IdeaDetailViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .padding(8.dp)
    ) {
        Text(
            text = storyTitle,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(8.dp)
        )

        // charalist

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState()),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            if (ideaDetailViewModel.storyCharaListState.value.size < 5) {
                Card(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .padding(16.dp)
                        .wrapContentSize()
                        .defaultMinSize(minWidth = 100.dp, minHeight = 150.dp)
                        .clickable{
                            clickCharaCreate()
                        }
                    ,
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = "追加",
                            textAlign = TextAlign.Center,)
                    }
                }
            }
            ideaDetailViewModel.storyCharaListState.value.forEach { item ->
                Card(modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .defaultMinSize(minWidth = 200.dp, minHeight = 150.dp)
                    .clickable{
                        ideaDetailViewModel.openCharaDetailAlert(story = item)
                    }
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = item.getCharaInfo(),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.charaDesc,
                            textAlign = TextAlign.Center,
                            maxLines = 4,
                            modifier = Modifier.padding(top = 6.dp)
                        )
                    }
                }
            }
        }

        var body: String = if (storyBody == null) "物語の内容を入力しましょう。" else storyBody
        Text(
            text = body,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(8.dp)
                .clickable{
                    clickUpdateStoryBody()
                }
        )


    }
}
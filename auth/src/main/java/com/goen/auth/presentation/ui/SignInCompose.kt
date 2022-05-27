package com.goen.auth.presentation.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.goen.auth.presentation.SignInMenu
import com.goen.utils.compose.DaikuAppTheme
import kotlinx.coroutines.launch

@Composable
fun SignInCompose(onSignIn: () -> Unit) {


    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val clickMenu: () -> Unit = {
        scope.launch {
            scaffoldState.drawerState.apply {
                if (isClosed) open() else close()
            }
        }
    }

    val clickMenuItem: (menu: SignInMenu) -> Unit = { menu: SignInMenu ->
        when(menu) {
            SignInMenu.GoogleSignIn->{
                onSignIn()
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { SignInTopBar(onClickMenu = clickMenu) },
        drawerContent = {
            DrawerContent(
                clickMenu = clickMenuItem
            )
        })
    {
        Column(modifier = Modifier.padding(16.dp)) {
            Button(onClick = onSignIn) {
                Text(text = "Googleアカウントでログイン")
            }
        }
    }
}

@Composable
fun SignInTopBar(onClickMenu: () -> Unit) {
    TopAppBar(
        title = {
            Text(
                text = "ログイン",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon =  {
            IconButton(onClick = { onClickMenu() }) {
                Icon(Icons.Filled.Menu, contentDescription = "open drawer")
            }
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}

@Composable
fun DrawerContent(
    clickMenu: (menu: SignInMenu) -> Unit
) {

    val items = listOf(
        SignInMenu.GoogleSignIn
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "ログイン",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            startIndent = 10.dp
        )
        items.forEach{item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        clickMenu(item)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(50.dp),
                    painter = rememberImagePainter(
                        data = item.icon,
                        builder = {
                            placeholder(item.icon)
                            transformations(CircleCropTransformation())
                        },
                    ),
                    contentDescription = item.title
                )
                Text(
                    text = item.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }


}
package com.goen.auth.presentation.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.goen.utils.compose.DaikuAppTheme

@Composable
fun PrivacyPolicyCompose(
    navController: NavHostController,
    viewUrl: String
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    DaikuAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { PrivacyPolicyTopBar() })
        {
            AndroidView(
                factory = ::WebView,
                update = { webView ->
                    webView.webViewClient = WebViewClient()
                    var webViewSetting = webView.settings
                    webViewSetting.javaScriptEnabled = true
                    webView.loadUrl(viewUrl)
                }
            )
        }
    }
}

@Composable
private fun PrivacyPolicyTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "プライバシーポリシー",
                color = DaikuAppTheme.colors.topAppBarTitle) },
        navigationIcon =  {
        },
        backgroundColor = DaikuAppTheme.colors.topAppBarColor
    )
}
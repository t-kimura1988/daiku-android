package com.goen.auth.presentation

import com.goen.auth.R

sealed class SignInMenu(var title: String, var icon: Int?) {
    object GoogleSignIn : SignInMenu("Googleアカウントでログイン", R.drawable.ic_btn_google_light_normal_ios)
    object PrivacyPolicy : SignInMenu("プライバシーポリシー", null)
    object TermsOfUse : SignInMenu("利用規約", null)

}
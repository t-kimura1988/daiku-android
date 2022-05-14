package com.goen.home

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Home : NavigationItem("home", R.drawable.ic_home, "Home")
    object Favorite : NavigationItem("favorite", R.drawable.ic_favorite, "お気に入り")
    object Profile : NavigationItem("profile", R.drawable.ic_person, "プロフィール")
}

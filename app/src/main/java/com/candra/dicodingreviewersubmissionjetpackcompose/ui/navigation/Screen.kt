package com.candra.dicodingreviewersubmissionjetpackcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Favorite : Screen("favorite")
    object Profile : Screen("profile")
    object DetailReviewer : Screen("home/{reviewerId}") {
        fun createRoute(reviewerId: Long) = "home/$reviewerId"
    }
}
package com.candra.dicodingreviewersubmissionjetpackcompose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.component.BottomBarNavigation
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.navigation.Screen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.detail.DetailReviewerScreen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.favorite.FavoriteReviewerScreen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.home.HomeScreen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.profile.AboutScreen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.theme.DicodingReviewerSubmissionJetpackComposeTheme

@Composable
fun DicodingReviewer(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailReviewer.route) {
                BottomBarNavigation(navController = navController)
            }
        }, modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateDetail = { reviewerId ->
                    navController.navigate(Screen.DetailReviewer.createRoute(reviewerId))
                })
            }
            composable(Screen.Favorite.route) {
                FavoriteReviewerScreen(navigateDetail = { reviewerId ->
                    navController.navigate(Screen.DetailReviewer.createRoute(reviewerId))
                })
            }
            composable(Screen.Profile.route) {
                AboutScreen()
            }
            composable(
                route = Screen.DetailReviewer.route,
                arguments = listOf(navArgument("reviewerId") {
                    type = NavType.LongType
                })
            ) {
                val id = it.arguments?.getLong("reviewerId") ?: -1L
                DetailReviewerScreen(id = id, navigationBack = { navController.navigateUp() })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DicodingReviewPreview() {
    DicodingReviewerSubmissionJetpackComposeTheme {
        DicodingReviewer()
    }
}

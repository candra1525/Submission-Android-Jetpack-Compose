package com.candra.dicodingreviewersubmissionjetpackcompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.navigation.NavItem
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.navigation.Screen

@Composable
fun BottomBarNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navItem = listOf(
            NavItem(
                title = stringResource(id = R.string.home),
                icon = Icons.Filled.Home,
                screen = Screen.Home,
                contentDescription = stringResource(id = R.string.home)
            ),
            NavItem(
                title = stringResource(id = R.string.favorite),
                icon = Icons.Filled.Star,
                screen = Screen.Favorite,
                contentDescription = stringResource(id = R.string.favorite)
            ),
            NavItem(
                title = stringResource(id = R.string.about),
                icon = Icons.Filled.AccountCircle,
                screen = Screen.Profile,
                contentDescription = stringResource(id = R.string.about_page)
            )
        )

        navItem.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleLarge.copy(fontSize = 15.sp)
                    )
                },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BottomBarNavigationPreview() {
    BottomBarNavigation(navController = NavHostController(LocalContext.current))
}
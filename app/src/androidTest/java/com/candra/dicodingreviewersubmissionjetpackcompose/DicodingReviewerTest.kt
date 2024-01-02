package com.candra.dicodingreviewersubmissionjetpackcompose

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.candra.dicodingreviewersubmissionjetpackcompose.model.KontributorReviewerData
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.navigation.Screen
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.theme.DicodingReviewerSubmissionJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DicodingReviewerTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingReviewerSubmissionJetpackComposeTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                DicodingReviewer(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentDestination(Screen.Home.route)
    }

    @Test
    fun bottomNav_work() {
        composeTestRule.onNodeWithStringId(R.string.home).performClick()
        navController.assertCurrentDestination(Screen.Home.route)
        composeTestRule.onNodeWithStringId(R.string.favorite).performClick()
        navController.assertCurrentDestination(Screen.Favorite.route)
        composeTestRule.onNodeWithStringId(R.string.about).performClick()
        navController.assertCurrentDestination(Screen.Profile.route)
    }

    @Test
    fun clickItem_navigateToDetail() {
        composeTestRule.onNodeWithStringTag(R.string.reviewer_list).performScrollToIndex(10)
        composeTestRule.onNodeWithText(KontributorReviewerData.kontributorReviewer[10].name)
            .performClick()
        navController.assertCurrentDestination(Screen.DetailReviewer.route)
        composeTestRule.onNodeWithText(KontributorReviewerData.kontributorReviewer[10].name)
            .assertIsDisplayed()
    }

    @Test
    fun addFavorite() {
        clickItem_navigateToDetail()
        composeTestRule.onNodeWithStringTag(R.string.fav_tag).performClick()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).assertExists()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).performClick()
        navHost_verifyStartDestination()
        composeTestRule.onNodeWithStringId(R.string.favorite).performClick()
        navController.assertCurrentDestination(Screen.Favorite.route)
        composeTestRule.onNodeWithStringTag(R.string.favorite_list).assertIsDisplayed()
        composeTestRule.onNodeWithText(KontributorReviewerData.kontributorReviewer[10].name)
            .performClick()
    }

    @Test
    fun removeFavorite() {
        addFavorite()
        composeTestRule.onNodeWithStringTag(R.string.fav_tag).performClick()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).assertExists()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).performClick()
    }

    @Test
    fun searchReviewer() {
        navHost_verifyStartDestination()
        composeTestRule.onNodeWithStringTag(R.string.reviewer_list).assertIsDisplayed()
        val searchBar = composeTestRule.onNodeWithStringId(R.string.search)
        searchBar.performTextInput("Bervianto Leo Pratama")
        composeTestRule.onAllNodesWithText("Bervianto Leo Pratama")[1].assertIsDisplayed()
            .performClick()
        navController.assertCurrentDestination(Screen.DetailReviewer.route)
        composeTestRule.onNodeWithText("Bervianto Leo Pratama").assertIsDisplayed()
    }

    @Test
    fun searchReviewerError() {
        navHost_verifyStartDestination()
        composeTestRule.onNodeWithStringTag(R.string.reviewer_list).assertIsDisplayed()
        val searchBar = composeTestRule.onNodeWithStringId(R.string.search)
        searchBar.performTextInput("Candra")
        composeTestRule.onNodeWithStringId(R.string.no_item).assertIsDisplayed()
    }
}
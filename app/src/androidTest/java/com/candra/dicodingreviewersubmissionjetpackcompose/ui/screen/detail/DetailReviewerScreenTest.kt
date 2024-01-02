package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import com.candra.dicodingreviewersubmissionjetpackcompose.model.KontributorReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.onNodeWithStringContentDesc
import com.candra.dicodingreviewersubmissionjetpackcompose.onNodeWithStringTag
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.theme.DicodingReviewerSubmissionJetpackComposeTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailReviewerScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val fakeReviewer = Reviewer(
        reviewer = KontributorReviewer(
            id = 4,
            name = "Nur Rohman",
            job = "Chief Product Officer at Dicoding Indonesia",
            other = "Kontributor Kelas Memulai Pemrograman dengan Kotlin, LINE API Expert, Associate Android Developer, Kotlin Enthusiast",
            photo = "https://media.licdn.com/dms/image/C5603AQFOo-PMJ_lLOA/profile-displayphoto-shrink_200_200/0/1614004379030?e=1704326400&v=beta&t=oYMwN_r0jYAgWVrG4yV4kKvnw6jVB19vf9LHXk-cRvk"
        ),
        isFavorite = false
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            DicodingReviewerSubmissionJetpackComposeTheme {
                DetailReviewerContent(
                    photo = fakeReviewer.reviewer.photo,
                    name = fakeReviewer.reviewer.name,
                    job = fakeReviewer.reviewer.job,
                    other = fakeReviewer.reviewer.other,
                    isFavorite = fakeReviewer.isFavorite,
                    onBackClick = { },
                    onAddFavorite = {}
                )
            }
        }
        composeTestRule.onRoot().printToLog("currentLabelExists")
    }

    @Test
    fun detailReviewer_isDisplayed() {
        composeTestRule.onNodeWithText(fakeReviewer.reviewer.name).assertIsDisplayed()
        composeTestRule.onNodeWithStringTag(R.string.photo_tag).assertExists()
        composeTestRule.onNodeWithStringTag(R.string.name_tag).assertExists()
        composeTestRule.onNodeWithStringTag(R.string.job_tag).assertExists()
        composeTestRule.onNodeWithStringTag(R.string.fav_tag).assertExists()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).assertExists()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).performClick()
    }

    @Test
    fun favoriteReviewer_isFavorite() {
        composeTestRule.onNodeWithStringTag(R.string.fav_tag).assertExists()
        composeTestRule.onNodeWithStringTag(R.string.fav_tag).performClick()
        composeTestRule.onNodeWithStringContentDesc(R.string.back_to_home).performClick()
    }
}
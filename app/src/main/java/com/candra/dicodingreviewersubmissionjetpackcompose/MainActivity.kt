package com.candra.dicodingreviewersubmissionjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.theme.DicodingReviewerSubmissionJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DicodingReviewerSubmissionJetpackComposeTheme {
                DicodingReviewer()
            }
        }
    }
}
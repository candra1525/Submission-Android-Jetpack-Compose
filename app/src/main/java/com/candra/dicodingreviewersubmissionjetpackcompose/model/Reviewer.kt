package com.candra.dicodingreviewersubmissionjetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reviewer(
    val reviewer: KontributorReviewer,
    val isFavorite: Boolean
) : Parcelable
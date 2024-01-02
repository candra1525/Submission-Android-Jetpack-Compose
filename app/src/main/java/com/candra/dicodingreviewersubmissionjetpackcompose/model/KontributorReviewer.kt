package com.candra.dicodingreviewersubmissionjetpackcompose.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KontributorReviewer(
    val id: Long,
    val name: String,
    val job: String,
    val other: String,
    val photo: String,
    val isFavorite: Boolean = false
) : Parcelable
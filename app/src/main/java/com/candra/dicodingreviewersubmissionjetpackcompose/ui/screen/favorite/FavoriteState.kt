package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.favorite

import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer

data class FavoriteState(
    val reviewerDicoding: List<Reviewer>,
    val isFavorite: Boolean
)

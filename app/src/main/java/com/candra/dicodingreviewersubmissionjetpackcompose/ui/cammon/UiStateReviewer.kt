package com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon

sealed class UiStateReviewer<out T : Any?> {
    object Loading : UiStateReviewer<Nothing>()
    data class Success<out T : Any>(val data: T) : UiStateReviewer<T>()
    data class Error(val errorMessage: String) : UiStateReviewer<Nothing>()
}
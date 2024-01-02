package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoriteReviewerViewModel(
    private val repository: DicodingReviewerRepository
) : ViewModel() {
    private val _listFavorite: MutableStateFlow<UiStateReviewer<FavoriteState>> =
        MutableStateFlow(UiStateReviewer.Loading)
    val listFavorite: MutableStateFlow<UiStateReviewer<FavoriteState>>
        get() = _listFavorite

    fun getFavoriteReviewer() {
        viewModelScope.launch {
            _listFavorite.value = UiStateReviewer.Loading
            repository.getAddedFavoriteReviewer().collect { favoriteReviewer ->
                _listFavorite.value = UiStateReviewer.Success(FavoriteState(favoriteReviewer, true))
            }
        }
    }
}
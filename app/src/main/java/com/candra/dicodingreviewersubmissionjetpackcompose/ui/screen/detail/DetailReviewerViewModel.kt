package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository
import com.candra.dicodingreviewersubmissionjetpackcompose.model.KontributorReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailReviewerViewModel(
    private val repository: DicodingReviewerRepository
) : ViewModel() {
    private val _reviewerList: MutableStateFlow<UiStateReviewer<Reviewer>> =
        MutableStateFlow(UiStateReviewer.Loading)
    val reviewerList: StateFlow<UiStateReviewer<Reviewer>> get() = _reviewerList

    fun getDetailReviewer(id: Long) {
        viewModelScope.launch {
            _reviewerList.value = UiStateReviewer.Loading
            _reviewerList.value = UiStateReviewer.Success(repository.getDetailReviewer(id))
        }
    }

    fun addFavorite(kontributorRev: KontributorReviewer, isFavorite: Boolean) {
        viewModelScope.launch {
            repository.updateFavoriteReviewer(kontributorRev.id, !isFavorite).collect {
                if (it) getDetailReviewer(kontributorRev.id)
            }
        }
    }
}
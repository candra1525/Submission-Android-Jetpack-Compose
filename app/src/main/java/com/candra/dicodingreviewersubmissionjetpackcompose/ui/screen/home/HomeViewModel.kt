package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository
import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DicodingReviewerRepository) : ViewModel() {
    private val _reviewerList: MutableStateFlow<UiStateReviewer<List<Reviewer>>> =
        MutableStateFlow(UiStateReviewer.Loading)
    val reviewerList: StateFlow<UiStateReviewer<List<Reviewer>>> get() = _reviewerList

    fun getAllReviewer() {
        viewModelScope.launch {
            repository.getData().catch {
                _reviewerList.value = UiStateReviewer.Error(it.message.toString())
            }.collect { dataReviewer ->
                _reviewerList.value = UiStateReviewer.Success(dataReviewer)
            }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchReviewerName(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            try {
                val results = repository.searchReviewerName(newQuery)
                _reviewerList.value = UiStateReviewer.Success(results)
            } catch (e: Exception) {
                _reviewerList.value = UiStateReviewer.Error(e.message.toString())
            }
        }
    }
}
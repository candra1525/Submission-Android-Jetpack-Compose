package com.candra.dicodingreviewersubmissionjetpackcompose.ui.vmf

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.detail.DetailReviewerViewModel
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.favorite.FavoriteReviewerViewModel
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: DicodingReviewerRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailReviewerViewModel::class.java)) {
            return DetailReviewerViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(FavoriteReviewerViewModel::class.java)) {
            return FavoriteReviewerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
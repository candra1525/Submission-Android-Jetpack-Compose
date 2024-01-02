package com.candra.dicodingreviewersubmissionjetpackcompose.di

import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository

object Injection {
    fun provideRepository(): DicodingReviewerRepository {
        return DicodingReviewerRepository.getInstance()
    }
}
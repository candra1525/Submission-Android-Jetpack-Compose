package com.candra.dicodingreviewersubmissionjetpackcompose.data

import com.candra.dicodingreviewersubmissionjetpackcompose.model.KontributorReviewerData
import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DicodingReviewerRepository {

    private val reviewer = mutableListOf<Reviewer>()

    init {
        if (reviewer.isEmpty()) {
            KontributorReviewerData.kontributorReviewer.forEach {
                reviewer.add(Reviewer(it, false))
            }
        }
    }

    fun getData(): Flow<List<Reviewer>> {
        return flowOf(reviewer)
    }

    fun searchReviewerName(query: String): List<Reviewer> {
        return reviewer.filter {
            it.reviewer.name.contains(query, ignoreCase = true)
        }
    }

    fun getDetailReviewer(id: Long): Reviewer {
        return reviewer.first {
            it.reviewer.id == id
        }
    }

    fun updateFavoriteReviewer(id: Long, isFavorite: Boolean): Flow<Boolean> {
        val index = reviewer.indexOfFirst { it.reviewer.id == id }
        val result = if (index >= 0) {
            val reviewers = reviewer[index]
            reviewer[index] = reviewers.copy(reviewer = reviewers.reviewer, isFavorite = isFavorite)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedFavoriteReviewer(): Flow<List<Reviewer>> {
        return getData()
            .map { reviewer ->
                reviewer.filter { reviewers ->
                    reviewers.isFavorite
                }
            }
    }

    companion object {
        @Volatile
        private var instance: DicodingReviewerRepository? = null

        fun getInstance(): DicodingReviewerRepository =
            instance ?: synchronized(this) {
                DicodingReviewerRepository().apply {
                    instance = this
                }
            }
    }
}
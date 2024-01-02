package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import com.candra.dicodingreviewersubmissionjetpackcompose.data.DicodingReviewerRepository
import com.candra.dicodingreviewersubmissionjetpackcompose.model.Reviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.component.ReviewerItem
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.component.SearchReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.vmf.ViewModelFactory

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(
            DicodingReviewerRepository()
        )
    ), navigateDetail: (Long) -> Unit
) {
    viewModel.reviewerList.collectAsState(initial = UiStateReviewer.Loading).value.let { uiState ->
        when (uiState) {
            is UiStateReviewer.Loading -> {
                viewModel.getAllReviewer()
            }

            is UiStateReviewer.Success -> {
                HomeContent(
                    uiState.data,
                    modifier = modifier,
                    navigateDetail = navigateDetail,
                    viewModel = viewModel

                )
            }

            is UiStateReviewer.Error -> {}
        }
    }
}

@Composable
fun HomeContent(
    reviewerList: List<Reviewer>,
    modifier: Modifier = Modifier,
    navigateDetail: (Long) -> Unit,
    viewModel: HomeViewModel
) {
    val query by viewModel.query
    val onClearQuery: () -> Unit = {
        viewModel.searchReviewerName("")
    }

    Column(modifier = modifier) {
        HomeTopBar(
            query = query, viewModel = viewModel, onClearQuery = onClearQuery, modifier = modifier
        )
        if (reviewerList.isEmpty()) {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                ListEmpty(modifier)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.testTag(stringResource(id = R.string.reviewer_list))
            ) {
                items(reviewerList) { data ->
                    ReviewerItem(photo = data.reviewer.photo,
                        name = data.reviewer.name,
                        job = data.reviewer.job,
                        modifier = modifier.clickable {
                            navigateDetail(data.reviewer.id)
                        })
                }
            }
        }
    }
}

@Composable
private fun ListEmpty(modifier: Modifier = Modifier) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.list_empty),
            contentDescription = stringResource(id = R.string.no_item),
            modifier = modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = stringResource(id = R.string.no_item),
            modifier = modifier.align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 24.sp
            )
        )
    }
}

@Composable
fun HomeTopBar(
    query: String, viewModel: HomeViewModel, onClearQuery: () -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(1f),
    ) {
        Image(
            painter = painterResource(id = R.drawable.home_banner_transparent),
            contentDescription = stringResource(id = R.string.ic_dicoding_reviewer),
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
        SearchReviewer(
            query = query,
            onQueryChange = viewModel::searchReviewerName,
            onClearQuery = onClearQuery,
            modifier = modifier,
        )
    }
}
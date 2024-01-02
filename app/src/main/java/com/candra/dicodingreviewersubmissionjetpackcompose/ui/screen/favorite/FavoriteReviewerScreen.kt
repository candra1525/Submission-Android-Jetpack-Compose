package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import com.candra.dicodingreviewersubmissionjetpackcompose.di.Injection
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.component.ReviewerItem
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.vmf.ViewModelFactory

@Composable
fun FavoriteReviewerScreen(
    viewModel: FavoriteReviewerViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ), navigateDetail: (Long) -> Unit
) {
    viewModel.listFavorite.collectAsState(initial = UiStateReviewer.Loading).value.let { uiState ->
        when (uiState) {
            is UiStateReviewer.Loading -> {
                viewModel.getFavoriteReviewer()
            }

            is UiStateReviewer.Success -> {
                FavoriteReviewerScreenContent(
                    favoriteState = uiState.data,
                    navigateDetail = navigateDetail,
                    modifier = Modifier
                )
            }

            is UiStateReviewer.Error -> {}
        }
    }
}

@Composable
fun FavoriteReviewerScreenContent(
    favoriteState: FavoriteState, navigateDetail: (Long) -> Unit, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(1f)
    ) {
        FavoriteTopBar(modifier = modifier)

        if (favoriteState.reviewerDicoding.isEmpty()) {
            Box(
                modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                ListEmpty(modifier)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier.testTag(stringResource(id = R.string.favorite_list))
            ) {
                items(favoriteState.reviewerDicoding) { data ->
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
fun FavoriteTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(1f)
            .shadow(4.dp, shape = MaterialTheme.shapes.small)
            .background(Color.White)
            .clip(RectangleShape),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.favorite_banner),
            contentDescription = stringResource(id = R.string.photo_reviewer)
        )
    }
}


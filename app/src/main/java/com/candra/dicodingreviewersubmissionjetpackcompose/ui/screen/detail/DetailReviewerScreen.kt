package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import com.candra.dicodingreviewersubmissionjetpackcompose.di.Injection
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.cammon.UiStateReviewer
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.component.OtherJob
import com.candra.dicodingreviewersubmissionjetpackcompose.ui.vmf.ViewModelFactory

@Composable
fun DetailReviewerScreen(
    id: Long,
    viewModel: DetailReviewerViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigationBack: () -> Unit,
) {
    viewModel.reviewerList.collectAsState(initial = UiStateReviewer.Loading).value.let { uiState ->
        when (uiState) {
            is UiStateReviewer.Loading -> {
                viewModel.getDetailReviewer(id)
            }

            is UiStateReviewer.Success -> {
                val data = uiState.data
                DetailReviewerContent(data.reviewer.photo,
                    data.reviewer.name,
                    data.reviewer.job,
                    data.reviewer.other,
                    data.isFavorite,
                    onBackClick = navigationBack,
                    onAddFavorite = { isFavorite: Boolean ->
                        viewModel.addFavorite(data.reviewer, isFavorite = isFavorite)
                    })
            }

            is UiStateReviewer.Error -> {}
            else -> {}
        }
    }
}


@Composable
fun DetailReviewerContent(
    photo: String,
    name: String,
    job: String,
    other: String,
    isFavorite: Boolean,
    onBackClick: () -> Unit,
    onAddFavorite: (isFavorite: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.fillMaxSize()) {
            DetailTopBar(onBackClick = onBackClick, modifier = modifier)
            LazyColumn(modifier = modifier) {
                item {
                    DetailImage(photo = photo, modifier = modifier)
                    DetailSpacer(space = 16, modifier = modifier)
                    DetailName(name = name, modifier = modifier)
                    DetailSpacer(space = 4, modifier = modifier)
                    DetailJob(job = job, modifier = modifier)
                    DetailSpacer(space = 12, modifier = modifier)
                }

                val otherJob = other.split(", ")
                items(otherJob) { list ->
                    OtherJob(text = list, modifier = modifier)
                }
            }
        }

        FloatingActionButton(
            onClick = {
                onAddFavorite(isFavorite)
            },
            modifier = modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
                .testTag("fab_favorite"),
            contentColor = MaterialTheme.colorScheme.primary,
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = if (isFavorite) stringResource(id = R.string.remove_fav) else stringResource(
                    id = R.string.add_fav
                ),
                tint = if (isFavorite) Color.Red else Color.White
            )
        }
    }
}

@Composable
fun DetailImage(photo: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.background_programmer),
            contentDescription = stringResource(id = R.string.background_detail),
            modifier = modifier
                .fillMaxWidth(1f)
                .height(150.dp)
                .alpha(0.5f),
            contentScale = ContentScale.Crop
        )
        AsyncImage(
            model = photo,
            contentDescription = stringResource(id = R.string.photo_reviewer),
            contentScale = ContentScale.Crop,
            error = painterResource(R.drawable.error_image),
            modifier = modifier
                .padding(top = 32.dp)
                .size(200.dp)
                .align(Alignment.Center)
                .clip(CircleShape)
                .testTag("photo_reviewer"),
        )
    }
}

@Composable
fun DetailName(name: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = name,
            modifier = modifier
                .padding(start = 8.dp)
                .testTag("name_reviewer"),
            fontSize = 20.sp,
            style = MaterialTheme.typography.titleLarge.copy(
                textAlign = TextAlign.Center
            )
        )
    }
}

@Composable
fun DetailJob(job: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = job,
            modifier = modifier
                .padding(start = 8.dp)
                .testTag("job_reviewer"),
            fontSize = 15.sp,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Justify
            )
        )
    }
}

@Composable
fun DetailSpacer(space: Int, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.padding(space.dp))
}

@Composable
fun DetailTopBar(onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(1f)
            .shadow(4.dp, shape = MaterialTheme.shapes.small)
            .background(Color.White)
            .clip(RectangleShape)
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}, modifier = modifier.padding(start = 16.dp)
        ) {
            Icon(imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_to_home),
                modifier = modifier.clickable { onBackClick() })
        }
        Text(
            text = stringResource(id = R.string.title_detail_reviewer),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge.copy(
                textAlign = TextAlign.Center
            )
        )
    }
}
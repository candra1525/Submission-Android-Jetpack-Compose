package com.candra.dicodingreviewersubmissionjetpackcompose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.candra.dicodingreviewersubmissionjetpackcompose.R


@Composable
fun ReviewerItem(
    photo: String,
    name: String,
    job: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .width(140.dp)
            .height(250.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            AsyncImage(
                model = photo,
                contentDescription = stringResource(id = R.string.photo_reviewer),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                error = painterResource(R.drawable.error_image),
                modifier = modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = modifier.padding(2.dp))
        Column(
            verticalArrangement = Arrangement.Center,
        )
        {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = name,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 15.sp
                    )
                )
            }
            Spacer(modifier = modifier.padding(2.dp))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = job,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 10.sp
                    )
                )
            }

        }
    }
}

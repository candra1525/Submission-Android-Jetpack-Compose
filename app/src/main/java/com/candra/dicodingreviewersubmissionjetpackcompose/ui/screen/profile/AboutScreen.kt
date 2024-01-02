package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.candra.dicodingreviewersubmissionjetpackcompose.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    About(modifier)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun About(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(1f)
    ) {
        AboutTopBar(modifier = modifier)

        LazyColumn(modifier = modifier.weight(1f)) {
            item {
                AboutSpacer(space = 20)
                Row(
                    modifier = modifier.padding(8.dp)
                ) {
                    AboutContent(modifier)
                }
                AboutSpacer(space = 20)
                Row(
                    modifier = modifier.padding(16.dp)
                ) {
                    AboutDescription(modifier)
                }
                AboutSpacer(space = 20)
                Row(
                    modifier = modifier.padding(16.dp)
                ) {
                    AboutSocialMedia(modifier = modifier)
                }
            }
        }
    }
}

@Composable
fun AboutContent(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.developer_profile),
            contentDescription = stringResource(id = R.string.profile_dev),
            modifier = modifier
                .size(100.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        AboutSpacer(space = 12)
        Column(
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = stringResource(id = R.string.name_dev),
                fontSize = 24.sp,
                style = MaterialTheme.typography.titleLarge
            )
            AboutSpacer(space = 2)
            Divider(
                modifier = modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .clip(RoundedCornerShape(4.dp))
            )
            AboutSpacer(space = 2)
            Text(
                text = stringResource(id = R.string.learning_path),
                fontSize = 12.sp,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun AboutDescription(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(1f)
    ) {
        Text(
            text = stringResource(id = R.string.desc_title),
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 15.sp
            ),
        )
        AboutSpacer(space = 4)
        val descText = stringResource(id = R.string.about_dev_description)
        Text(
            text = descText,
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Justify, fontSize = 12.sp
            ),
            modifier = modifier.fillMaxWidth(),
        )
    }
}

@Composable
fun AboutSocialMedia(modifier: Modifier = Modifier) {
    val urlLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    Column(modifier = modifier.fillMaxWidth(1f)) {
        Row(modifier = modifier.fillMaxWidth()) {
            Text(
                text = stringResource(id = R.string.sosmed_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 15.sp
                ),
            )
        }
        AboutSpacer(space = 8)
        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            TemplateSosMed(modifier = modifier,
                painter = painterResource(id = R.drawable.linkedin_24),
                text = stringResource(id = R.string.linkedin_title),
                onClick = { openLink("https://www.linkedin.com/in/candra1525/", urlLauncher) })
            TemplateSosMed(modifier = modifier,
                painterResource(id = R.drawable.instagram_24),
                text = stringResource(id = R.string.instagram_title),
                onClick = { openLink("https://www.instagram.com/candracandra1525/", urlLauncher) })
        }
    }
}

private fun openLink(url: String, activity: ManagedActivityResultLauncher<Intent, ActivityResult>) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    activity.launch(intent)
}

@Composable
fun TemplateSosMed(
    modifier: Modifier = Modifier, painter: Painter, text: String, onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .shadow(4.dp, shape = MaterialTheme.shapes.medium)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
    ) {
        Column {
            Row(
                modifier = modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painter,
                    contentDescription = stringResource(id = R.string.logo_medsos),
                    modifier = modifier.size(24.dp)
                )
                AboutSpacer(space = 8)
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp
                    ),
                )
            }
        }
    }
}

@Composable
fun AboutTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(1f)
            .shadow(4.dp, shape = MaterialTheme.shapes.small)
            .background(Color.White)
            .clip(RectangleShape),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.about_banner),
            contentDescription = stringResource(
                id = R.string.about_dev
            )
        )
    }
}

@Composable
fun AboutSpacer(space: Int, modifier: Modifier = Modifier) {
    Spacer(modifier = modifier.padding(space.dp))
}
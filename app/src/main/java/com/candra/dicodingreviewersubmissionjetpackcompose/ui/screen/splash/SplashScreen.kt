package com.candra.dicodingreviewersubmissionjetpackcompose.ui.screen.splash

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.candra.dicodingreviewersubmissionjetpackcompose.MainActivity
import com.candra.dicodingreviewersubmissionjetpackcompose.R
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashContent(modifier = Modifier)
        }

        lifecycleScope.launchWhenCreated {
            delay(SPLASH_SCREEN_DURATION)
            nextScreen(this@SplashScreen)
        }
    }

    @Composable
    fun SplashContent(modifier: Modifier = Modifier) {
        val context = LocalContext.current
        val gifDrawable = getDrawableFromResource(context, R.drawable.splash_screen_dicoding)
        val painter = rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = gifDrawable).apply(block = fun ImageRequest.Builder.() {
                crossfade(true)
            }).build()
        )
        Box(modifier = modifier.fillMaxSize()) {
            Image(
                painter = painter,
                contentDescription = stringResource(id = R.string.ic_dicoding_reviewer),
                modifier = modifier.fillMaxSize()
            )
        }
    }

    private fun nextScreen(activity: Activity) {
        val intent = Intent(activity, MainActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }

    private fun getDrawableFromResource(context: Context, drawableId: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableId)
    }

    private companion object {
        const val SPLASH_SCREEN_DURATION = 2000L
    }
}




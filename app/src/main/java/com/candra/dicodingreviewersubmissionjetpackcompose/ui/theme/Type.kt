package com.candra.dicodingreviewersubmissionjetpackcompose.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.candra.dicodingreviewersubmissionjetpackcompose.R


private val quickSand = FontFamily(
    Font(R.font.quicksand_bold, FontWeight.W600),
    Font(R.font.quicksand_semibold, FontWeight.W500),
    Font(R.font.quicksand_medium, FontWeight.W400),
)

val quickSandTypography = Typography(
    titleLarge = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.W600,
    ),
    titleMedium = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.W500,
    ),
    bodyMedium = TextStyle(
        fontFamily = quickSand,
        fontWeight = FontWeight.W400,
    ),
)
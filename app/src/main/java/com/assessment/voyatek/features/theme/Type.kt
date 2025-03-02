package com.assessment.voyatek.features.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.assessment.voyatek.R

// Set of Material typography styles to start with
val Typography = Typography(
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_light)),
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.5.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.dm_sans_semibold)),
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)
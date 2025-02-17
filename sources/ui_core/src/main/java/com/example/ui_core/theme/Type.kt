package com.example.ui_core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui_core.R

val typography = Typography(
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.tenor_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 28.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.tenor_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.tenor_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 19.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.tenor_sans_regular)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp
    )
)
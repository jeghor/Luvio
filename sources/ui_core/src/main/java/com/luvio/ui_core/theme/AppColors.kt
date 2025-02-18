package com.luvio.ui_core.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

object AppColors {
    val primary = Color(0xFFFF5E78)
    val secondary = Color(0xFFF6F6F6)
    val background = Color(0xFFFFFFFF)
    val transparent = Color(0x00000000)
    val textPrimary = Color(0xFF333333)
    val textHint = Color(0xFF888888)
}

interface ColorPalette {
    val primary: Color
    val secondary: Color
    val background: Color
    val transparent: Color
    val textPrimary: Color
    val textHint: Color

    val materialColors: ColorScheme
}

fun lightColorPalette(): ColorPalette = object : ColorPalette {
    override val primary: Color = AppColors.primary
    override val secondary: Color = AppColors.secondary
    override val background: Color = AppColors.background
    override val transparent: Color = AppColors.transparent
    override val textPrimary: Color = AppColors.textPrimary
    override val textHint: Color = AppColors.textHint

    override val materialColors: ColorScheme = lightColorScheme(
        primary = primary,
        surface = textPrimary,
        onSurface = background
    )
}
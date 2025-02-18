package com.luvio.ui_core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun LuvioTheme(
    typography: Typography = com.luvio.ui_core.theme.typography,
    content: @Composable () -> Unit
) {
    val colors = lightColorPalette()
    CompositionLocalProvider(
        LocalColor provides colors,
        LocalTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = colors.materialColors,
            typography = com.luvio.ui_core.theme.typography,
            content = content
        )
    }
}

object AppTheme {

    val colors: ColorPalette
        @Composable get() = LocalColor.current

    val typography: Typography
        @Composable get() = LocalTypography.current

    val sizes: AppSizes
        @Composable get() = AppSizes()
}

internal val LocalColor = staticCompositionLocalOf { lightColorPalette() }

internal val LocalTypography = staticCompositionLocalOf { typography }
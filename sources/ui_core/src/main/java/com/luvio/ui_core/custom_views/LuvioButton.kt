package com.luvio.ui_core.custom_views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.theme.AppColors
import com.luvio.ui_core.theme.AppTheme

@Composable
fun LuvioButton(
    modifier: Modifier,
    text: String,
    filled: Boolean = true,
    smallText: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(AppTheme.sizes.rounded),
        colors = buttonColors(
            containerColor = if (filled) AppTheme.colors.primary else AppTheme.colors.background,
            contentColor = if (filled) AppTheme.colors.background else AppTheme.colors.primary,
            disabledContainerColor = if (filled) AppColors.lightPink else AppTheme.colors.background,
            disabledContentColor = if (filled) AppTheme.colors.background else AppTheme.colors.primary
        ),
        border = if (filled) {
            BorderStroke(0.dp, Color.White)
        } else {
            BorderStroke(AppTheme.sizes.border, AppTheme.colors.primary)
        },
        onClick = onClick,
        enabled = enabled
    ) {
        Text(
            text = text,
            style = if (smallText) AppTheme.typography.bodySmall else AppTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
fun LuvioButtonPreview() {
    Surface {
        LuvioButton(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Login",
            filled = true,
            smallText = false
        ) {
            // onClick callback
        }
    }
}
package com.example.ui_core.custom_views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui_core.theme.AppTheme

@Composable
fun LuvioTextField(
    modifier: Modifier,
    value: String? = null,
    placeholder: String? = null,
    @DrawableRes endIcon: Int
) {
    var value by remember { mutableStateOf(value) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background, shape = RoundedCornerShape(12.dp))
            .border(BorderStroke(1.dp, AppTheme.colors.primary), shape = RoundedCornerShape(12.dp)),
        textStyle = AppTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = AppTheme.colors.textHint,
            focusedTextColor = AppTheme.colors.textPrimary
        ),
        value = value ?: "",
        placeholder = {
            Text(
                text = placeholder ?: "",
                style = AppTheme.typography.bodyMedium
            )
        },
        trailingIcon = {
            Icon(
                painterResource(endIcon),
                tint = AppTheme.colors.primary,
                contentDescription = "End Icon"
            )
        },
        onValueChange = {
            value = it
        }
    )
}

@Preview
@Composable
fun LuvioTextFieldPreview() {
    Surface {
        LuvioTextField(
            modifier = Modifier,
            placeholder = "Enter email",
            endIcon = com.example.ui_atoms.R.drawable.ic_email
        )
    }
}
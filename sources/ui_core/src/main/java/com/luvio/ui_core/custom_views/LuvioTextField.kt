package com.luvio.ui_core.custom_views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.theme.AppTheme

@Composable
fun LuvioTextField(
    modifier: Modifier,
    value: String? = null,
    placeholder: String? = null,
    isPassword: Boolean = false,
    @DrawableRes endIcon: Int,
    onTextListener: (value: String) -> Unit = {}
) {
    var value by remember { mutableStateOf(value) }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .border(
                BorderStroke(AppTheme.sizes.defaultBorder, AppTheme.colors.primary),
                shape = RoundedCornerShape(
                    AppTheme.sizes.defaultRounded
                )
            ),
        textStyle = AppTheme.typography.bodyMedium,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colors.background,
            unfocusedContainerColor = AppTheme.colors.background,
            disabledContainerColor = AppTheme.colors.background,
            errorContainerColor = AppTheme.colors.background,
            focusedIndicatorColor = AppTheme.colors.transparent,
            unfocusedIndicatorColor = AppTheme.colors.transparent,
            unfocusedTextColor = AppTheme.colors.textPrimary,
            disabledTextColor = AppTheme.colors.textPrimary,
            focusedTextColor = AppTheme.colors.textPrimary
        ),
        shape = RoundedCornerShape(AppTheme.sizes.defaultRounded),
        value = value ?: "",
        placeholder = {
            Text(
                text = placeholder ?: "",
                style = AppTheme.typography.bodyMedium,
                color = AppTheme.colors.textHint
            )
        },
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            Icon(
                painterResource(endIcon),
                tint = AppTheme.colors.primary,
                contentDescription = "End Icon"
            )
        },
        onValueChange = {
            onTextListener(it)
            value = it
        }
    )
}

@Preview
@Composable
fun LuvioTextFieldPreview() {
    Surface(
        Modifier.padding(32.dp)
    ) {
        LuvioTextField(
            modifier = Modifier,
            placeholder = "Enter email",
            endIcon = com.luvio.ui_atoms.R.drawable.ic_email
        )
    }
}
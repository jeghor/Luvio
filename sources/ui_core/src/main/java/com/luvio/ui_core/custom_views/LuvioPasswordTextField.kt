package com.luvio.ui_core.custom_views

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.luvio.ui_core.theme.AppTheme

@Composable
fun LuvioPasswordTextField(
    modifier: Modifier,
    value: String? = null,
    error: String? = null,
    placeholder: String? = null,
    maxLines: Int = 1,
    @DrawableRes endIcon: Int,
    onTextListener: (value: String) -> Unit = {}
) {
    var passwordVisible by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(interactionSource) {
        interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> {
                    passwordVisible = true
                }
                is PressInteraction.Release, is PressInteraction.Cancel -> {
                    passwordVisible = false
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    BorderStroke(
                        AppTheme.sizes.border,
                        if (error == null) AppTheme.colors.primary else AppTheme.colors.error
                    ),
                    shape = RoundedCornerShape(
                        AppTheme.sizes.rounded
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
            shape = RoundedCornerShape(AppTheme.sizes.rounded),
            value = value ?: "",
            placeholder = {
                Text(
                    text = placeholder ?: "",
                    style = AppTheme.typography.bodyMedium,
                    color = AppTheme.colors.textHint
                )
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                autoCorrectEnabled = false
            ),
            trailingIcon = {
                Icon(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                passwordVisible = true
                                tryAwaitRelease()
                                passwordVisible = false
                            }
                        )
                    },
                    painter = painterResource(endIcon),
                    tint = AppTheme.colors.primary,
                    contentDescription = "End Icon"
                )
            },
            maxLines = maxLines,
            onValueChange = {
                onTextListener(it)
            }
        )

        if (error != null) {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(com.luvio.ui_atoms.R.drawable.ic_error),
                    modifier = Modifier
                        .padding(end = 4.dp)
                        .size(14.dp),
                    tint = AppTheme.colors.error,
                    contentDescription = "Error icon",
                )
                Text(
                    text = error,
                    color = AppTheme.colors.error,
                    style = AppTheme.typography.bodySmall
                )
            }
        }
    }
}

@Preview
@Composable
fun LuvioPasswordTextFieldPreview() {
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
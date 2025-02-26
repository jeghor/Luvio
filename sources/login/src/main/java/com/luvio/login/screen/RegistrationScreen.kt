package com.luvio.login.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.luvio.ui_atoms.R
import com.luvio.ui_core.custom_views.LuvioButton
import com.luvio.ui_core.custom_views.LuvioTextField
import com.luvio.ui_core.theme.AppTheme

@Composable
fun RegistrationScreen(
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val background = painterResource(id = R.drawable.one_heart_background)
        Image(
            painter = background,
            contentDescription = "Background image",
            modifier = Modifier
                .fillMaxSize()
        )

        val (title, emailField, passwordField, repeatPasswordField, description, regButton) = createRefs()

        Text(
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 64.dp),
            text = stringResource(com.luvio.ui_core.R.string.registration),
            color = AppTheme.colors.primary,
            style = AppTheme.typography.titleLarge
        )

        LuvioTextField(
            modifier = Modifier
                .constrainAs(emailField) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 32.dp,
                    start = AppTheme.sizes.defaultPadding,
                    end = AppTheme.sizes.defaultPadding
                ),
            placeholder = stringResource(com.luvio.ui_core.R.string.email_placeholder),
            endIcon = R.drawable.ic_email
        )

        LuvioTextField(
            modifier = Modifier
                .constrainAs(passwordField) {
                    top.linkTo(emailField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.defaultPadding,
                    start = AppTheme.sizes.defaultPadding,
                    end = AppTheme.sizes.defaultPadding
                ),
            placeholder = stringResource(com.luvio.ui_core.R.string.password_placeholder),
            isPassword = true,
            endIcon = R.drawable.ic_lock
        )

        LuvioTextField(
            modifier = Modifier
                .constrainAs(repeatPasswordField) {
                    top.linkTo(passwordField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.defaultPadding,
                    start = AppTheme.sizes.defaultPadding,
                    end = AppTheme.sizes.defaultPadding
                ),
            placeholder = stringResource(com.luvio.ui_core.R.string.confirm_password_placeholder),
            isPassword = true,
            endIcon = R.drawable.ic_lock
        )

        Text(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(repeatPasswordField.bottom)
                    start.linkTo(parent.start)
                }
                .padding(AppTheme.sizes.defaultPadding),
            text = stringResource(com.luvio.ui_core.R.string.registration_description),
            color = AppTheme.colors.textHint,
            style = AppTheme.typography.bodySmall
        )

        LuvioButton(
            modifier = Modifier
                .padding(
                    start = AppTheme.sizes.defaultPadding,
                    end = AppTheme.sizes.defaultPadding,
                    bottom = AppTheme.sizes.defaultButtonHeight
                )
                .constrainAs(regButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(AppTheme.sizes.defaultButtonHeight)
                .fillMaxWidth(),
            text = stringResource(com.luvio.ui_core.R.string.registration)
        ) {

        }
    }
}

@Preview
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen()
}
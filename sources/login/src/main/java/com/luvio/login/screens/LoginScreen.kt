package com.luvio.login.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.luvio.ui_core.custom_views.LuvioButton
import com.luvio.ui_core.custom_views.LuvioTextField
import com.luvio.ui_core.theme.AppTheme
import com.luvio.ui_atoms.R

@Composable
fun LoginScreen(
    navController: NavController
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {

        val background = painterResource(id = R.drawable.background)
        Image(
            painter = background,
            contentDescription = "Background image",
            modifier = Modifier
                .fillMaxSize()

        )

        val (logo, emailField, passwordField, forgotPasswordText, loginButton, regButton) = createRefs()

        val logoModifier = createGuidelineFromTop(0.2f)
        val loginButtonModifier = createGuidelineFromBottom(0f)

        Image(
            painter = painterResource(R.drawable.ic_logo_with_text),
            contentDescription = "Logo",
            modifier = Modifier.constrainAs(logo) {
                top.linkTo(logoModifier)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )

        LuvioTextField(
            modifier = Modifier
                .constrainAs(emailField) {
                    top.linkTo(logo.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = AppTheme.sizes.defaultPadding),
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

        Text(
            modifier = Modifier
                .constrainAs(forgotPasswordText) {
                    top.linkTo(passwordField.bottom)
                    end.linkTo(parent.end)
                }
                .padding(AppTheme.sizes.defaultPadding),
            text = stringResource(com.luvio.ui_core.R.string.forgot_password),
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium
        )

        LuvioButton(
            modifier = Modifier
                .constrainAs(loginButton) {
                    bottom.linkTo(regButton.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(AppTheme.sizes.defaultButtonHeight)
                .fillMaxWidth()
                .padding(
                    start = AppTheme.sizes.defaultPadding,
                    end = AppTheme.sizes.defaultPadding
                ),
            text = stringResource(com.luvio.ui_core.R.string.login)
        ) {}

        Text(
            modifier = Modifier
                .constrainAs(regButton) {
                    bottom.linkTo(loginButtonModifier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.defaultPadding,
                    bottom = AppTheme.sizes.defaultPadding
                ),
            text = stringResource(com.luvio.ui_core.R.string.registration),
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium
        )
    }
}
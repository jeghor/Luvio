package com.luvio.login.screen

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.login.Registration
import com.luvio.login.di.LoginComponent
import com.luvio.login.viewmodel.LoginViewModel
import com.luvio.ui_atoms.R
import com.luvio.ui_core.custom_views.*
import com.luvio.ui_core.dialog.LuvioBottomDialog
import com.luvio.ui_core.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController
) {
    val application = LocalContext.current.applicationContext as Application
    val component = remember {
        LoginComponent.create((application as AppWithFacade).getFacade())
    }

    val viewModel: LoginViewModel = viewModel(factory = component.viewModelFactory())
    val scope = rememberCoroutineScope()

    val showIncorrectDataDialog = remember { mutableStateOf(false) }
    val showSuccessLoginDialog = remember { mutableStateOf(false) }
    val showSomethingWentWrongDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        launch { viewModel.eventIncorrectData.collect { showIncorrectDataDialog.value = true } }
        launch { viewModel.eventSuccessLogin.collect { showSuccessLoginDialog.value = true } }
        launch { viewModel.eventSomethingWentWrong.collect { showIncorrectDataDialog.value = true } }
    }

    if (showIncorrectDataDialog.value) {
        LuvioBottomDialog(
            scope,
            dragHandle = null,
            message = stringResource(com.luvio.ui_core.R.string.incorrect_data)
        ) { showIncorrectDataDialog.value = it }
    }

    if (showSuccessLoginDialog.value) {
        LuvioBottomDialog(
            scope,
            dragHandle = null,
            message = "Успешный логин!"
        ) { showSuccessLoginDialog.value = it }
    }

    if (showSomethingWentWrongDialog.value) {
        LuvioBottomDialog(
            scope,
            dragHandle = null,
            message = stringResource(com.luvio.ui_core.R.string.something_went_wrong)
        ) { showSomethingWentWrongDialog.value = it }
    }

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

        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val (logo, emailField, passwordField, forgotPasswordText, loginButton, regButton) = createRefs()

        val logoModifier = createGuidelineFromTop(0f)
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
                .padding(horizontal = AppTheme.sizes.padding),
            value = email.value,
            placeholder = stringResource(com.luvio.ui_core.R.string.email_placeholder),
            endIcon = R.drawable.ic_email
        ) {
            email.value = it
        }

        LuvioPasswordTextField(
            modifier = Modifier
                .constrainAs(passwordField) {
                    top.linkTo(emailField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.padding,
                    start = AppTheme.sizes.padding,
                    end = AppTheme.sizes.padding
                ),
            value = password.value,
            placeholder = stringResource(com.luvio.ui_core.R.string.password_placeholder),
            endIcon = R.drawable.ic_lock
        ) {
            password.value = it
        }

        Text(
            modifier = Modifier
                .constrainAs(forgotPasswordText) {
                    top.linkTo(passwordField.bottom)
                    end.linkTo(parent.end)
                }
                .padding(AppTheme.sizes.padding),
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
                .height(AppTheme.sizes.buttonHeight)
                .fillMaxWidth()
                .padding(
                    start = AppTheme.sizes.padding, end = AppTheme.sizes.padding
                ),
            text = stringResource(com.luvio.ui_core.R.string.login)
        ) {
            viewModel.login(email.value, password.value)
        }

        Text(
            modifier = Modifier
                .constrainAs(regButton) {
                    bottom.linkTo(loginButtonModifier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.padding, bottom = AppTheme.sizes.padding
                )
                .imePadding()
                .clickable {
                    navController.navigate(Registration)
                },
            text = stringResource(com.luvio.ui_core.R.string.registration),
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium
        )
    }
}
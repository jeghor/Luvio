package com.luvio.login.screen

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.login.di.RegistrationComponent
import com.luvio.login.viewmodel.*
import com.luvio.ui_atoms.R
import com.luvio.ui_core.custom_views.*
import com.luvio.ui_core.dialog.LuvioBottomDialog
import com.luvio.ui_core.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    val application = LocalContext.current.applicationContext as Application
    val component = remember {
        RegistrationComponent.create((application as AppWithFacade).getFacade())
    }

    val viewModel: RegistrationViewModel = viewModel(factory = component.viewModelFactory())
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        val showSuccessRegistrationDialog = remember { mutableStateOf(false) }
        val errorMessage = remember { mutableStateOf<String?>(null) }

        LaunchedEffect(Unit) {
            launch { viewModel.eventSuccessRegistration.collect { showSuccessRegistrationDialog.value = true } }
            launch { viewModel.eventError.collect { errorMessage.value = it } }
        }

        if (showSuccessRegistrationDialog.value) {
            LuvioBottomDialog(
                scope,
                dragHandle = null,
                message = stringResource(com.luvio.ui_core.R.string.success_registration)
            ) {
                showSuccessRegistrationDialog.value = it
                navController.popBackStack()
            }
        }

        if (errorMessage.value != null) {
            LuvioBottomDialog(
                scope,
                dragHandle = null,
                message = errorMessage.value.takeIf { it?.isNotEmpty() == true }
                    ?: stringResource(com.luvio.ui_core.R.string.error_registration)
            ) { errorMessage.value = null }
        }

        val email = remember { mutableStateOf("") }
        val firstPassword = remember { mutableStateOf("") }
        val secondPassword = remember { mutableStateOf("") }

        val emailValidation by viewModel.stateEmailValidation.collectAsStateWithLifecycle(null)
        val firstPasswordValidation by viewModel.stateFirstPasswordValidation.collectAsStateWithLifecycle(null)
        val secondPasswordValidation by viewModel.stateSecondPasswordValidation.collectAsStateWithLifecycle(null)
        val combinedPasswordValidation by viewModel.stateCombinedPasswordValidation.collectAsStateWithLifecycle(null)

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
                    start = AppTheme.sizes.padding,
                    end = AppTheme.sizes.padding
                ),
            value = email.value,
            error = email.value.takeIf { it.isNotEmpty() }
                ?.let { getEmailErrorDescription(emailValidation) },
            placeholder = stringResource(com.luvio.ui_core.R.string.email_placeholder),
            endIcon = R.drawable.ic_email
        ) {
            viewModel.updateEmail(it)
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
            value = firstPassword.value,
            error = firstPassword.value.takeIf { it.isNotEmpty() }
                ?.let { getPasswordErrorDescription(firstPasswordValidation) },
            placeholder = stringResource(com.luvio.ui_core.R.string.password_placeholder),
            endIcon = R.drawable.ic_lock
        ) {
            viewModel.updateFirstPassword(it)
            firstPassword.value = it
        }

        LuvioPasswordTextField(
            modifier = Modifier
                .constrainAs(repeatPasswordField) {
                    top.linkTo(passwordField.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.padding,
                    start = AppTheme.sizes.padding,
                    end = AppTheme.sizes.padding
                ),
            value = secondPassword.value,
            error = secondPassword.value.takeIf { it.isNotEmpty() }
                ?.let { getPasswordErrorDescription(secondPasswordValidation) },
            placeholder = stringResource(com.luvio.ui_core.R.string.confirm_password_placeholder),
            endIcon = R.drawable.ic_lock
        ) {
            viewModel.updateSecondPassword(it)
            secondPassword.value = it
        }

        Text(
            modifier = Modifier
                .constrainAs(description) {
                    top.linkTo(repeatPasswordField.bottom)
                    start.linkTo(parent.start)
                }
                .padding(AppTheme.sizes.padding),
            text = stringResource(com.luvio.ui_core.R.string.registration_description),
            color = AppTheme.colors.textHint,
            style = AppTheme.typography.bodySmall
        )

        LuvioButton(
            modifier = Modifier
                .padding(
                    start = AppTheme.sizes.padding,
                    end = AppTheme.sizes.padding,
                    bottom = AppTheme.sizes.buttonHeight
                )
                .imePadding()
                .constrainAs(regButton) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(AppTheme.sizes.buttonHeight)
                .fillMaxWidth(),
            text = stringResource(com.luvio.ui_core.R.string.registration),
            enabled = combinedPasswordValidation is PasswordValidation.Valid && emailValidation is EmailValidation.Valid
        ) {
            viewModel.startRegistration(email.value, firstPassword.value)
        }
    }
}

@Composable
fun getEmailErrorDescription(validation: EmailValidation?): String? {
    return when (validation) {
        EmailValidation.Invalid -> stringResource(com.luvio.ui_core.R.string.error_email_incorrect)
        else -> null
    }
}

@Composable
fun getPasswordErrorDescription(validation: PasswordValidation?): String? {
    return when (validation) {
        PasswordValidation.Invalid -> stringResource(com.luvio.ui_core.R.string.error_password_incorrect)
        PasswordValidation.Mismatch -> stringResource(com.luvio.ui_core.R.string.error_password_mismatch)
        else -> null
    }
}
package com.luvio.login.screen

import android.app.Application
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.login.di.OnboardingComponent
import com.luvio.login.utils.TestTag
import com.luvio.login.viewmodel.OnboardingState
import com.luvio.login.viewmodel.OnboardingViewModel
import com.luvio.ui_core.R
import com.luvio.ui_core.custom_views.LuvioButton
import com.luvio.ui_core.theme.AppTheme

@Composable
fun OnboardingScreen() {
    val application = LocalContext.current.applicationContext as Application
    val component = remember {
        OnboardingComponent.create((application as AppWithFacade).getFacade())
    }
    val viewModel: OnboardingViewModel = viewModel(factory = component.viewModelFactory())

    val stateStep = viewModel.stateOnboarding.collectAsState()

    when (val step = stateStep.value) {
        is OnboardingState.Step -> {
            StepOnboardingContent(
                imageId = step.imageId,
                titleText = stringResource(step.titleId),
                descriptionText = stringResource(step.descriptionId),
                buttonNextText = stringResource(step.buttonNextTextId),
                onNext = { viewModel.nextStep() },
                onSkip = { viewModel.openLoginScreen() }
            )
        }

        else -> viewModel.nextStep()
    }
}

@Composable
fun StepOnboardingContent(
    @DrawableRes imageId: Int,
    titleText: String,
    descriptionText: String,
    buttonNextText: String,
    onNext: () -> Unit,
    onSkip: () -> Unit
) {
    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .padding(WindowInsets.navigationBars.asPaddingValues())
    ) {
        val (image, title, description, nextButton, skipButton) = createRefs()

        val imageModifier = createGuidelineFromTop(0.15f)
        val skipButtonModifier = createGuidelineFromBottom(0f)

        Image(
            painter = painterResource(imageId),
            contentDescription = "step onboarding image",
            modifier = Modifier
                .testTag(TestTag.ONBOARDING_IMAGE)
                .constrainAs(image) {
                    top.linkTo(imageModifier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .width(300.dp)
                .height(300.dp)
                .padding(bottom = 56.dp)
        )

        Text(
            modifier = Modifier
                .testTag(TestTag.TEXT_TITLE)
                .constrainAs(title) {
                    top.linkTo(image.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(horizontal = AppTheme.sizes.padding),
            text = titleText,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.titleLarge
        )

        Text(
            modifier = Modifier
                .testTag(TestTag.TEXT_DESCRIPTION)
                .constrainAs(description) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = 8.dp,
                    start = AppTheme.sizes.padding,
                    end = AppTheme.sizes.padding
                ),
            text = descriptionText,
            textAlign = TextAlign.Center,
            color = AppTheme.colors.textHint,
            style = AppTheme.typography.bodyMedium
        )

        LuvioButton(
            modifier = Modifier
                .testTag(TestTag.NEXT_BUTTON)
                .constrainAs(nextButton) {
                    bottom.linkTo(skipButton.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .height(AppTheme.sizes.buttonHeight)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            text = buttonNextText,
            onClick = onNext
        )

        Text(
            modifier = Modifier
                .testTag(TestTag.SKIP_BUTTON)
                .constrainAs(skipButton) {
                    bottom.linkTo(skipButtonModifier)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(
                    top = AppTheme.sizes.padding,
                    bottom = AppTheme.sizes.padding
                )
                .clickable { onSkip() },
            text = stringResource(R.string.skip),
            color = AppTheme.colors.textPrimary,
            style = AppTheme.typography.bodyMedium,
        )
    }
}
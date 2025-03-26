package com.luvio.login.screen

import androidx.activity.ComponentActivity
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.luvio.login.OnboardingStepFactory
import com.luvio.login.utils.TestTag
import com.luvio.login.viewmodel.OnboardingState
import com.luvio.login.viewmodel.StepCount
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class OnboardingScreenTest {

    @get:Rule
    val composeActivityTestRule = createAndroidComposeRule<ComponentActivity>()

    private fun setupOnboardingScreen(
        initialStep: OnboardingState.Step,
        onNext: (current: OnboardingState.Step) -> OnboardingState.Step,
        onSkip: () -> Unit = {}
    ) {
        composeActivityTestRule.setContent {
            var step by remember { mutableStateOf(initialStep) }

            StepOnboardingContent(
                imageId = step.imageId,
                titleText = stringResource(step.titleId),
                descriptionText = stringResource(step.descriptionId),
                buttonNextText = stringResource(step.buttonNextTextId),
                onNext = { step = onNext(step) },
                onSkip = onSkip
            )
        }
        composeActivityTestRule.waitForIdle()
    }

    @Test
    fun onboardingScreen_DisplaysAllElements() {
        val step = OnboardingStepFactory.getFirstStepState()

        setupOnboardingScreen(
            initialStep = step,
            onNext = { step },
            onSkip = {  }
        )

        // Verify UI elements visibility
        composeActivityTestRule.onNodeWithTag(TestTag.ONBOARDING_IMAGE).assertIsDisplayed()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertIsDisplayed()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_DESCRIPTION).assertIsDisplayed()
        composeActivityTestRule.onNodeWithTag(TestTag.NEXT_BUTTON).assertIsDisplayed()
        composeActivityTestRule.onNodeWithTag(TestTag.SKIP_BUTTON).assertIsDisplayed()
    }

    @Test
    fun onboardingScreen_GoToSecondStep() {
        val firstStep = OnboardingStepFactory.getFirstStepState()
        val secondStep = OnboardingStepFactory.getSecondStepState()

        setupOnboardingScreen(
            initialStep = firstStep,
            onNext = { secondStep },
            onSkip = {  }
        )

        val firstStepTitle = composeActivityTestRule.activity.getString(firstStep.titleId)
        val secondStepTitle = composeActivityTestRule.activity.getString(secondStep.titleId)

        composeActivityTestRule.waitForIdle()

        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertExists()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertTextEquals(firstStepTitle)

        composeActivityTestRule.onNodeWithTag(TestTag.NEXT_BUTTON).performClick()

        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertExists()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertTextEquals(secondStepTitle)
    }

    @Test
    fun onboardingScreen_GoToThirdStep() {
        val firstStep = OnboardingStepFactory.getFirstStepState()
        val secondStep = OnboardingStepFactory.getSecondStepState()
        val thirdStep = OnboardingStepFactory.getThirdStepState()

        setupOnboardingScreen(
            initialStep = firstStep,
            onNext = { current ->
                when (current.step) {
                    StepCount.FIRST -> secondStep
                    StepCount.SECOND -> thirdStep
                    StepCount.THIRD -> current
                }
            },
            onSkip = { }
        )

        val firstStepTitle = composeActivityTestRule.activity.getString(firstStep.titleId)
        val secondStepTitle = composeActivityTestRule.activity.getString(secondStep.titleId)
        val thirdStepTitle = composeActivityTestRule.activity.getString(thirdStep.titleId)

        composeActivityTestRule.waitForIdle()

        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertExists()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertTextEquals(firstStepTitle)

        composeActivityTestRule.onNodeWithTag(TestTag.NEXT_BUTTON).performClick()

        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertExists()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertTextEquals(secondStepTitle)

        composeActivityTestRule.onNodeWithTag(TestTag.NEXT_BUTTON).performClick()

        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertExists()
        composeActivityTestRule.onNodeWithTag(TestTag.TEXT_TITLE).assertTextEquals(thirdStepTitle)
    }
}

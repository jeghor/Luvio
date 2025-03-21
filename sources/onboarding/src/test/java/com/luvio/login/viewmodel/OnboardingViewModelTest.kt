package com.luvio.login.viewmodel

import com.luvio.api.LoginMediator
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * @see OnboardingViewModel
 */
class OnboardingViewModelTest {

    private val loginMediator: LoginMediator = mockk()
    private val viewModel: OnboardingViewModel = OnboardingViewModel(loginMediator)

    @Test
    fun `should go to first step`() {
        // Check init state
        assertEquals(OnboardingState.Uninitialized, viewModel.stateOnboarding.value)

        viewModel.nextStep() // go to First step

        val currentStepState = viewModel.stateOnboarding.value  //Init value Uninitialized
        assertEquals(StepCount.FIRST, (currentStepState as? OnboardingState.Step)?.step)
    }

    @Test
    fun `should go to second step from first`() {
        // Check init state
        assertEquals(OnboardingState.Uninitialized, viewModel.stateOnboarding.value)

        viewModel.nextStep() // go to First step

        var currentStepState = viewModel.stateOnboarding.value  //Init value Uninitialized
        assertEquals(StepCount.FIRST, (currentStepState as? OnboardingState.Step)?.step)

        viewModel.nextStep() // go to Second step
        currentStepState = viewModel.stateOnboarding.value
        assertEquals(StepCount.SECOND, (currentStepState as? OnboardingState.Step)?.step)
    }

    @Test
    fun `should go to third step from second`() {
        // Check init state
        assertEquals(OnboardingState.Uninitialized, viewModel.stateOnboarding.value)

        viewModel.nextStep() // go to First step
        var currentStepState = viewModel.stateOnboarding.value  //Init value Uninitialized
        assertEquals(StepCount.FIRST, (currentStepState as? OnboardingState.Step)?.step)

        viewModel.nextStep() // go to Second step
        currentStepState = viewModel.stateOnboarding.value
        assertEquals(StepCount.SECOND, (currentStepState as? OnboardingState.Step)?.step)

        viewModel.nextStep() // go to Third step
        currentStepState = viewModel.stateOnboarding.value
        assertEquals(StepCount.THIRD, (currentStepState as? OnboardingState.Step)?.step)
    }
}
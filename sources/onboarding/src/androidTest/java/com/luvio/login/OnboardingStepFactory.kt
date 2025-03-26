package com.luvio.login

import com.luvio.login.viewmodel.OnboardingState
import com.luvio.login.viewmodel.StepCount
import com.luvio.ui_atoms.R

object OnboardingStepFactory {

    fun getFirstStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.FIRST,
        imageId = R.drawable.img_first_step_onboarding,
        titleId = com.luvio.ui_core.R.string.first_step_title,
        descriptionId = com.luvio.ui_core.R.string.first_step_description
    )

    fun getSecondStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.SECOND,
        imageId = R.drawable.img_second_step_onboarding,
        titleId = com.luvio.ui_core.R.string.second_step_title,
        descriptionId = com.luvio.ui_core.R.string.second_step_description
    )

    fun getThirdStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.THIRD,
        imageId = R.drawable.img_third_step_onboarding,
        titleId = com.luvio.ui_core.R.string.third_step_title,
        descriptionId = com.luvio.ui_core.R.string.third_step_description,
        buttonNextTextId = com.luvio.ui_core.R.string.begin
    )
}
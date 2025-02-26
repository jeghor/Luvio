package com.luvio.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.luvio.api.LoginMediator
import com.luvio.ui_atoms.R
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class OnboardingViewModel(
    private val loginMediator: LoginMediator
) : ViewModel() {

    private val _stateOnboarding = MutableStateFlow<OnboardingState>(OnboardingState.Uninitialized)
    val stateOnboarding = _stateOnboarding.asStateFlow()

    fun nextStep() {
        _stateOnboarding.update {
            when {
                it is OnboardingState.Uninitialized -> getFirstStepState()
                else -> {
                    val step = it as OnboardingState.Step

                    when (step.step) {
                        StepCount.FIRST -> getSecondStepState()
                        StepCount.SECOND -> getThirdStepState()
                        StepCount.THIRD -> {
                            openLoginScreen()
                            step
                        }
                    }
                }
            }
        }
    }

    fun openLoginScreen() {
        loginMediator.openStartScreen()
    }

    private fun getFirstStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.FIRST,
        imageId = R.drawable.img_first_step_onboarding,
        titleId = com.luvio.ui_core.R.string.first_step_title,
        descriptionId = com.luvio.ui_core.R.string.first_step_description
    )

    private fun getSecondStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.SECOND,
        imageId = R.drawable.img_second_step_onboarding,
        titleId = com.luvio.ui_core.R.string.second_step_title,
        descriptionId = com.luvio.ui_core.R.string.second_step_description
    )

    private fun getThirdStepState(): OnboardingState.Step = OnboardingState.Step(
        step = StepCount.THIRD,
        imageId = R.drawable.img_third_step_onboarding,
        titleId = com.luvio.ui_core.R.string.third_step_title,
        descriptionId = com.luvio.ui_core.R.string.third_step_description,
        buttonNextTextId = com.luvio.ui_core.R.string.begin
    )
}

sealed interface OnboardingState {

    data object Uninitialized : OnboardingState

    data class Step(
        val step: StepCount,
        val imageId: Int,
        val titleId: Int,
        val descriptionId: Int,
        val buttonNextTextId: Int = com.luvio.ui_core.R.string.next
    ) : OnboardingState
}

enum class StepCount() {
    FIRST, SECOND, THIRD
}

class OnboardingViewModelFactory @Inject constructor(
    private val loginMediator: LoginMediator
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OnboardingViewModel(loginMediator) as T
    }
}
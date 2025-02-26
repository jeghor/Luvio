package com.luvio.login

import com.luvio.api.LoginMediator
import com.luvio.core.api.mediator.ActivityProvider
import com.luvio.core.api.mediator.Navigator
import javax.inject.Inject

class LoginMediatorImpl @Inject constructor(
    private val activityProvider: ActivityProvider
) : LoginMediator {

    override fun openStartScreen() {
        (activityProvider.getActivity() as? Navigator)?.getController()?.navigate(Login)
    }
}
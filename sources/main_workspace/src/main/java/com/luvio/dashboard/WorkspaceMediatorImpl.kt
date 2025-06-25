package com.luvio.dashboard

import com.luvio.api.WorkspaceMediator
import com.luvio.core.api.mediator.ActivityProvider
import com.luvio.core.api.mediator.Navigator
import javax.inject.Inject

class WorkspaceMediatorImpl @Inject constructor(
    private val activityProvider: ActivityProvider
): WorkspaceMediator {

    override fun openWorkspace() {
        (activityProvider.getActivity() as? Navigator)?.getController()?.navigate(WorkspaceScreen.Workspace)
    }
}
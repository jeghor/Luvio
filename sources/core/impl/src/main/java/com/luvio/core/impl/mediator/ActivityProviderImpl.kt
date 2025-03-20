package com.luvio.core.impl.mediator

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.luvio.core.api.mediator.ActivityProvider
import java.lang.ref.WeakReference

class ActivityProviderImpl(app: Application) : ActivityProvider {

    private var activityRef: WeakReference<Activity>? = null

    override fun setCurrentActivity(activity: Activity) {
        activityRef = WeakReference(activity)
    }

    override fun clearCurrentActivity() {
        activityRef?.clear()
    }

    init {
        app.registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                setCurrentActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {
                setCurrentActivity(activity)
            }

            override fun onActivityResumed(activity: Activity) {
                setCurrentActivity(activity)
            }

            override fun onActivityPaused(activity: Activity) {
                clearCurrentActivity()
            }

            override fun onActivityStopped(activity: Activity) {
                clearCurrentActivity()
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

            override fun onActivityDestroyed(activity: Activity) {
                clearCurrentActivity()
            }
        })
    }

    override fun getActivity(): Activity? = activityRef?.get()
}
package com.luvio.core.api.mediator

import android.app.Activity

interface ActivityProvider {

    fun setCurrentActivity(activity: Activity)

    fun clearCurrentActivity(activity: Activity)

    fun getActivity(): Activity?
}
package com.luvio.map

import android.annotation.SuppressLint
import android.content.Context
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap

@SuppressLint("MissingPermission")
fun MapboxMap.enableUserLocation(context: Context) {
    val locationComponent = locationComponent

    val locationComponentActivationOptions =
        LocationComponentActivationOptions.builder(context, style!!)
            .useDefaultLocationEngine(true)
            .build()

    locationComponent.activateLocationComponent(locationComponentActivationOptions)
    locationComponent.isLocationComponentEnabled = true
    locationComponent.cameraMode = CameraMode.TRACKING
    locationComponent.renderMode = RenderMode.COMPASS
}
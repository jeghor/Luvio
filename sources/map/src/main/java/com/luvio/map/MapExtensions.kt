package com.luvio.map

import android.annotation.SuppressLint
import android.content.Context
import org.maplibre.android.location.LocationComponentActivationOptions
import org.maplibre.android.location.modes.CameraMode
import org.maplibre.android.location.modes.RenderMode
import org.maplibre.android.maps.MapLibreMap

@SuppressLint("MissingPermission")
fun MapLibreMap.enableUserLocation(context: Context) {
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
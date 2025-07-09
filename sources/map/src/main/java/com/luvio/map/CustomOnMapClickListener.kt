package com.luvio.map

import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.style.sources.GeoJsonSource

class CustomOnMapClickListener(
    private val map: MapLibreMap,
    private val onSinglePointClickCallback: OnSinglePointClickCallback
) : MapLibreMap.OnMapClickListener {

    override fun onMapClick(point: LatLng): Boolean {

        val screenPoint = map.projection.toScreenLocation(point)

        val clusterFeatures = map.queryRenderedFeatures(
            screenPoint,
            MapUtil.CIRCLE_POINT_LAYER_ID,
            MapUtil.COUNT_POINT_LAYER_ID
        )
        if (clusterFeatures.isNotEmpty()) {
            val feature = clusterFeatures[0]

            val source = map.style?.getSourceAs<GeoJsonSource>(MapUtil.CUSTOM_SOURCE_ID)
            source?.getClusterExpansionZoom(feature)
            source?.getClusterExpansionZoom(feature)?.let { zoom ->
                map.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(point, zoom.toDouble())
                )
            }

            return true
        }

        val singlePointFeatures =
            map.queryRenderedFeatures(screenPoint, MapUtil.SINGLE_POINT_LAYER_ID)
        if (singlePointFeatures.isNotEmpty()) {
            val feature = singlePointFeatures[0]

            val props = feature.properties()?.toString()
            // Показать BottomSheetDialog с данными
            if (props != null) {
                onSinglePointClickCallback.onClick(props)
            }

            return true
        }

        return false
    }
}

fun interface OnSinglePointClickCallback {

    fun onClick(info: String?)
}
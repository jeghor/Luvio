package com.luvio.map

import org.maplibre.android.geometry.LatLng

object MapUtil {

    const val CUSTOM_MAP_URL =
        "https://api.maptiler.com/maps/0197ea08-0a78-7a56-903a-172319f46e2a/style.json?key=${ApiKey.MAP_TILER_KEY}"

    val MINSK_COORDINATES = LatLng(53.9022, 27.5667)

    const val CUSTOM_SOURCE_ID = "CUSTOM_SOURCE_ID"
    const val CAFE_POINT_IMAGE_ID = "cafe_point"
    const val SINGLE_POINT_LAYER_ID = "single-point"
    const val CIRCLE_POINT_LAYER_ID = "cluster-circle"
    const val COUNT_POINT_LAYER_ID = "cluster-count"

    const val POINT_COUNT = "point_count"
    const val POINT_COUNT_ABBREVIATED = "point_count_abbreviated"

    const val LAYER_FONT = "Roboto Medium"
}
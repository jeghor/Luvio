package com.luvio.map.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.*
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.*
import com.google.android.gms.location.LocationServices
import com.luvio.map.*
import com.luvio.ui_atoms.R
import com.luvio.ui_core.theme.AppColors
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.camera.CameraUpdateFactory
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.*
import org.maplibre.android.style.expressions.Expression
import org.maplibre.android.style.layers.CircleLayer
import org.maplibre.android.style.layers.Property.ICON_ANCHOR_BOTTOM
import org.maplibre.android.style.layers.PropertyFactory.*
import org.maplibre.android.style.layers.SymbolLayer
import org.maplibre.android.style.sources.GeoJsonOptions
import org.maplibre.android.style.sources.GeoJsonSource

@SuppressLint("MissingPermission")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CustomMapView() {
    val context = LocalContext.current
    val mapView = rememberMapViewWithLifecycle(context)
    val locationPermissionState = rememberPermissionState(android.Manifest.permission.ACCESS_FINE_LOCATION)
    var userLocation by remember { mutableStateOf<LatLng?>(null) }
    var mapboxMap by remember { mutableStateOf<MapLibreMap?>(null) }

    LaunchedEffect(Unit) {
        locationPermissionState.launchPermissionRequest()
    }

    LaunchedEffect(locationPermissionState.status.isGranted, mapView) {
        if (locationPermissionState.status.isGranted) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    userLocation = LatLng(it.latitude, it.longitude)
                }
            }
        }
    }

    LaunchedEffect(userLocation, mapboxMap) {
        if (userLocation != null && mapboxMap != null) {
            mapboxMap?.animateCamera(
                CameraUpdateFactory.newLatLngZoom(userLocation!!, 15.0)
            )
        }
    }

    val showPlaceInfoBottomSheet = remember { mutableStateOf("") }

    if (showPlaceInfoBottomSheet.value.isNotEmpty()) {
        PlaceInfoBottomSheet(showPlaceInfoBottomSheet.value) {
            showPlaceInfoBottomSheet.value = ""
        }
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            mapView.apply {
                getMapAsync { map ->
                    mapboxMap = map

                    map.setStyle(
                        Style.Builder().fromUri(MapUtil.CUSTOM_MAP_URL)
                    ) { style ->
                        val singlePointImageDrawable = ResourcesCompat.getDrawable(
                            context.resources,
                            R.drawable.ic_map_point,
                            null
                        )
                        if (singlePointImageDrawable != null) {
                            style.addImage(MapUtil.CAFE_POINT_IMAGE_ID, singlePointImageDrawable)
                        }

                        val inputStream =
                            context.resources.openRawResource(com.luvio.map.R.raw.points_cafe)
                        val geoJsonString = inputStream.bufferedReader().use { it.readText() }

                        val geoJsonSource = GeoJsonSource(
                            MapUtil.CUSTOM_SOURCE_ID,
                            geoJsonString,
                            GeoJsonOptions()
                                .withCluster(true)
                                .withClusterMaxZoom(14)
                                .withClusterRadius(50)
                        )
                        style.addSource(geoJsonSource)

                        val singlePointLayer =
                            SymbolLayer(MapUtil.SINGLE_POINT_LAYER_ID, MapUtil.CUSTOM_SOURCE_ID)
                                .withProperties(
                                    iconImage(MapUtil.CAFE_POINT_IMAGE_ID),
                                    iconAllowOverlap(true),
                                    iconAnchor(ICON_ANCHOR_BOTTOM),
                                    iconSize(1.5f)
                                )
                                .withFilter(
                                    Expression.not(Expression.has(MapUtil.POINT_COUNT))
                                )

                        style.addLayer(singlePointLayer)

                        val clusterCircleLayer = CircleLayer(
                            MapUtil.CIRCLE_POINT_LAYER_ID,
                            MapUtil.CUSTOM_SOURCE_ID
                        )
                            .withProperties(
                                circleColor(AppColors.background.toArgb()),
                                circleStrokeColor(AppColors.primary.toArgb()),
                                circleStrokeWidth(2f),
                                circleRadius(20f)
                            )
                            .withFilter(
                                Expression.has(MapUtil.POINT_COUNT)
                            )

                        val clusterCountLayer = SymbolLayer(MapUtil.COUNT_POINT_LAYER_ID, MapUtil.CUSTOM_SOURCE_ID)
                            .withProperties(
                                textField(Expression.get(MapUtil.POINT_COUNT_ABBREVIATED)),
                                textSize(16f),
                                textColor(AppColors.primary.toArgb()),
                                textFont(arrayOf(MapUtil.LAYER_FONT)),
                                textIgnorePlacement(true),
                                textAllowOverlap(true)
                            )
                            .withFilter(
                                Expression.has(MapUtil.POINT_COUNT)
                            )

                        style.addLayer(clusterCircleLayer)
                        style.addLayer(clusterCountLayer)


                        val target = userLocation ?: MapUtil.MINSK_COORDINATES
                        map.cameraPosition = CameraPosition.Builder()
                            .target(target)
                            .zoom(15.0)
                            .build()

                        map.enableUserLocation(context)
                    }

                    map.addOnMapClickListener(CustomOnMapClickListener(map) {
                        showPlaceInfoBottomSheet.value = it ?: ""
                    })
                }
            }
        }
    )
}

@Composable
fun rememberMapViewWithLifecycle(context: Context): MapView {
    MapLibre.getInstance(context)

    val mapView = remember {
        MapView(context)
    }

    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle, mapView) {
        val observer = object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                when (event) {
                    Lifecycle.Event.ON_START -> mapView.onStart()
                    Lifecycle.Event.ON_RESUME -> mapView.onResume()
                    Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                    Lifecycle.Event.ON_STOP -> mapView.onStop()
                    Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                    else -> {}
                }
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return mapView
}
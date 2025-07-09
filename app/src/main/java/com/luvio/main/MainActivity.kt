package com.luvio.main

import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.luvio.core.api.mediator.*
import com.luvio.ui_core.theme.AppColors
import javax.inject.Inject

class MainActivity : ComponentActivity(), Navigator {

    private var navController: NavHostController? = null

    @Inject
    lateinit var activityProvider: ActivityProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                AppColors.transparent.toArgb(),
                AppColors.textPrimary.toArgb()
            )
        )
        setContent {
            navController = rememberNavController()
            LuvioApp(navController!!)
        }
    }

    override fun onResume() {
        super.onResume()
        activityProvider.setCurrentActivity(this)
    }

    override fun onPause() {
        super.onPause()
        activityProvider.clearCurrentActivity()
    }

    override fun getController(): NavHostController? = navController
}
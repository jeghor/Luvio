package com.luvio.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.luvio.core.api.mediator.*
import javax.inject.Inject

class MainActivity : ComponentActivity(), Navigator {

    private var navController: NavHostController? = null

    @Inject
    lateinit var activityProvider: ActivityProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent.create((application as AppWithFacade).getFacade()).inject(this)

        enableEdgeToEdge()
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
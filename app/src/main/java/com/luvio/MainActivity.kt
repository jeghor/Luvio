package com.luvio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.luvio.core.api.mediator.ActivityProvider
import com.luvio.core.api.mediator.AppWithFacade
import com.luvio.core.api.mediator.Navigator
import javax.inject.Inject

class MainActivity : ComponentActivity(), Navigator {

    var navController: NavHostController? = null
        private set

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
        activityProvider.clearCurrentActivity(this)
    }

    override fun getController(): NavHostController? = navController
}
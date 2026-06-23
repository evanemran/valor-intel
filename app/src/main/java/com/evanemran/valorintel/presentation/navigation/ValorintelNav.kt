package com.evanemran.valorintel.presentation.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.evanemran.valorintel.presentation.modules.home.HomeScreen

object Routes {
    const val HOME = "home"
    const val DETAILS = "details"
    const val ARG_AGENT = "arg_agent"
}

@Composable
fun ValorintelNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onAgentClick = { url ->
                },
            )
        }
    }
}
package com.evanemran.valorintel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.evanemran.valorintel.presentation.modules.agents.AgentDetailsScreen
import com.evanemran.valorintel.presentation.modules.home.HomeScreen

object Routes {
    const val HOME = "home"
    const val AGENT_DETAILS = "agent_details/{agentUuid}"

    fun agentDetails(agentUuid: String) = "agent_details/$agentUuid"
}

@Composable
fun ValorintelNav() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) {
            HomeScreen(
                onAgentClick = { agent ->
                    agent.uuid?.let { uuid ->
                        navController.navigate(Routes.agentDetails(uuid))
                    }
                },
            )
        }

        composable(
            route = Routes.AGENT_DETAILS,
            arguments = listOf(
                navArgument("agentUuid") { type = NavType.StringType },
            ),
        ) { backStackEntry ->
            val agentUuid = backStackEntry.arguments?.getString("agentUuid") ?: return@composable
            AgentDetailsScreen(
                agentUuid = agentUuid,
                onBackClick = { navController.popBackStack() },
            )
        }
    }
}

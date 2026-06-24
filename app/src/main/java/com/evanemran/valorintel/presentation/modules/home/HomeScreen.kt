package com.evanemran.valorintel.presentation.modules.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.presentation.modules.agents.AgentsScreen
import com.evanemran.valorintel.presentation.theme.AgentRed

@Composable
fun HomeScreen(
    onAgentClick: (Agent) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        bottomBar = {
            HomeBottomBar(
                selectedTab = state.selectedTab,
                onTabSelected = viewModel::selectTab,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (state.selectedTab) {
                HomeTab.Agents -> AgentsScreen(onAgentClick = onAgentClick)
                HomeTab.Weapons,
//                HomeTab.Discover,
                HomeTab.Maps,
                HomeTab.Cosmetics -> EmptyTabPlaceholder(tab = state.selectedTab)
            }
        }
    }
}

@Composable
private fun HomeBottomBar(
    selectedTab: HomeTab,
    onTabSelected: (HomeTab) -> Unit,
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = AgentRed,
    ) {
        HomeTab.entries.forEach { tab ->
            NavigationBarItem(
                selected = selectedTab == tab,
                onClick = { onTabSelected(tab) },
                icon = {
                    Icon(
                        imageVector = tab.icon,
                        contentDescription = tab.label,
                    )
                },
                label = { Text(text = tab.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AgentRed,
                    selectedTextColor = AgentRed,
                    indicatorColor = AgentRed.copy(alpha = 0.12f),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                ),
            )
        }
    }
}

@Composable
private fun EmptyTabPlaceholder(tab: HomeTab) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "${tab.label}\nComing soon",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.Gray,
        )
    }
}

private val HomeTab.icon: ImageVector
    get() = when (this) {
        HomeTab.Agents -> Icons.Default.Person
        HomeTab.Weapons -> Icons.Default.Build
//        HomeTab.Discover -> Icons.Default.Search
        HomeTab.Maps -> Icons.Default.Map
        HomeTab.Cosmetics -> Icons.Default.Star
    }

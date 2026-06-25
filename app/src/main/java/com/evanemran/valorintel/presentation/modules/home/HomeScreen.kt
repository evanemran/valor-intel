package com.evanemran.valorintel.presentation.modules.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.presentation.modules.agents.AgentsScreen
import com.evanemran.valorintel.presentation.modules.cosmetics.CosmeticsScreen
import com.evanemran.valorintel.presentation.modules.maps.MapsScreen
import com.evanemran.valorintel.presentation.modules.weapons.WeaponsScreen
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun HomeScreen(
    onAgentClick: (Agent) -> Unit = {},
    onWeaponClick: (Weapon) -> Unit = {},
    onMapClick: (ValorantMap) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        containerColor = ValorantDark,
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
                HomeTab.Weapons -> WeaponsScreen(onWeaponClick = onWeaponClick)
                HomeTab.Maps -> MapsScreen(onMapClick = onMapClick)
                HomeTab.Cosmetics -> CosmeticsScreen()
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
        containerColor = ValorantDark,
        contentColor = ValorantRed,
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
                    selectedIconColor = ValorantRed,
                    selectedTextColor = ValorantRed,
                    indicatorColor = ValorantRed.copy(alpha = 0.2f),
                    unselectedIconColor = Color.White.copy(alpha = 0.5f),
                    unselectedTextColor = Color.White.copy(alpha = 0.5f),
                ),
            )
        }
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

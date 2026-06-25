package com.evanemran.valorintel.presentation.modules.weapons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.presentation.components.WeaponCard
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun WeaponsScreen(
    onWeaponClick: (Weapon) -> Unit = {},
    viewModel: WeaponsViewModel = viewModel(factory = WeaponsViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantDark),
    ) {
        WeaponsTopBar()

        if (!state.isLoading && state.error == null && state.weapons.isNotEmpty()) {
            WeaponCategoryTabs(
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onCategorySelected = viewModel::selectCategory,
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            WeaponsContainer(
                state = state,
                onRefresh = { viewModel.loadWeapons(forceFresh = true) },
                onWeaponClick = onWeaponClick,
            )
        }
    }
}

@Composable
private fun WeaponsTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ValorantDark)
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        Text(
            text = "WEAPONS",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeaponCategoryTabs(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
) {
    val tabs = remember(categories) {
        listOf(WeaponCategoryUtil.ALL) + categories
    }
    val selectedIndex = tabs.indexOf(selectedCategory).coerceAtLeast(0)

    PrimaryScrollableTabRow(
        selectedTabIndex = selectedIndex,
        containerColor = ValorantDark,
        contentColor = Color.White,
        edgePadding = 16.dp,
        divider = {},
        indicator = {
            TabRowDefaults.PrimaryIndicator(
                modifier = Modifier.tabIndicatorOffset(selectedIndex, matchContentSize = true),
                color = Color.White,
            )
        },
    ) {
        tabs.forEachIndexed { index, category ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onCategorySelected(category) },
                text = {
                    Text(
                        text = category,
                        fontWeight = if (selectedIndex == index) FontWeight.Bold else FontWeight.Normal,
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(alpha = 0.7f),
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WeaponsContainer(
    state: WeaponsUiState,
    onRefresh: () -> Unit,
    onWeaponClick: (Weapon) -> Unit,
) {
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = ValorantRed)
            }
        }

        state.error != null -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "Failed to load data.\n${state.error}",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = 0.7f),
                    )
                }
            }
        }

        state.filteredWeapons.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No weapons found.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                )
            }
        }

        else -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.filteredWeapons) { weapon ->
                        WeaponCard(
                            weapon = weapon,
                            onClick = { onWeaponClick(weapon) },
                        )
                    }
                }
            }
        }
    }
}

package com.evanemran.valorintel.presentation.modules.cosmetics

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evanemran.valorintel.domain.models.Buddy
import com.evanemran.valorintel.domain.models.PlayerCard
import com.evanemran.valorintel.domain.models.PlayerTitle
import com.evanemran.valorintel.domain.models.Spray
import com.evanemran.valorintel.presentation.components.CosmeticItemCard
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun CosmeticsScreen(
    viewModel: CosmeticsViewModel = viewModel(factory = CosmeticsViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantDark),
    ) {
        CosmeticsTopBar()

        CosmeticTabs(
            selectedTab = state.selectedTab,
            onTabSelected = viewModel::selectTab,
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            CosmeticsContainer(
                state = state,
                onRefresh = viewModel::refresh,
            )
        }
    }
}

@Composable
private fun CosmeticsTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ValorantDark)
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        Text(
            text = "COSMETICS",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CosmeticTabs(
    selectedTab: CosmeticTab,
    onTabSelected: (CosmeticTab) -> Unit,
) {
    val tabs = CosmeticTab.entries
    val selectedIndex = tabs.indexOf(selectedTab).coerceAtLeast(0)

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
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = selectedIndex == index,
                onClick = { onTabSelected(tab) },
                text = {
                    Text(
                        text = tab.label,
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
private fun CosmeticsContainer(
    state: CosmeticsUiState,
    onRefresh: () -> Unit,
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

        else -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                when (state.selectedTab) {
                    CosmeticTab.Buddies -> BuddyGrid(buddies = state.buddies)
                    CosmeticTab.Sprays -> SprayGrid(sprays = state.sprays)
                    CosmeticTab.PlayerCards -> PlayerCardGrid(cards = state.playerCards)
                    CosmeticTab.PlayerTitles -> PlayerTitleGrid(titles = state.playerTitles)
                }
            }
        }
    }
}

@Composable
private fun BuddyGrid(buddies: List<Buddy>) {
    if (buddies.isEmpty()) {
        EmptyCosmeticMessage()
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(buddies, key = { it.uuid ?: it.displayName ?: it.hashCode() }) { buddy ->
            CosmeticItemCard(
                name = buddy.displayName ?: "N/A",
                imageUrl = buddy.displayIcon,
            )
        }
    }
}

@Composable
private fun SprayGrid(sprays: List<Spray>) {
    if (sprays.isEmpty()) {
        EmptyCosmeticMessage()
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(sprays, key = { it.uuid ?: it.displayName ?: it.hashCode() }) { spray ->
            CosmeticItemCard(
                name = spray.displayName ?: "N/A",
                imageUrl = spray.displayIcon ?: spray.fullIcon,
            )
        }
    }
}

@Composable
private fun PlayerCardGrid(cards: List<PlayerCard>) {
    if (cards.isEmpty()) {
        EmptyCosmeticMessage()
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(cards, key = { it.uuid ?: it.displayName ?: it.hashCode() }) { card ->
            CosmeticItemCard(
                name = card.displayName ?: "N/A",
                imageUrl = card.smallArt ?: card.displayIcon ?: card.wideArt,
            )
        }
    }
}

@Composable
private fun PlayerTitleGrid(titles: List<PlayerTitle>) {
    if (titles.isEmpty()) {
        EmptyCosmeticMessage()
        return
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(titles, key = { it.uuid ?: it.displayName ?: it.hashCode() }) { title ->
            CosmeticItemCard(
                name = title.displayName ?: "N/A",
                imageUrl = null,
                subtitle = title.titleText ?: title.displayName,
            )
        }
    }
}

@Composable
private fun EmptyCosmeticMessage() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "No items found.",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White.copy(alpha = 0.7f),
        )
    }
}

package com.evanemran.valorintel.presentation.modules.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.presentation.components.MapCard
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun MapsScreen(
    onMapClick: (ValorantMap) -> Unit = {},
    viewModel: MapsViewModel = viewModel(factory = MapsViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantDark),
    ) {
        MapsTopBar()

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            MapsContainer(
                state = state,
                onRefresh = { viewModel.loadMaps(forceFresh = true) },
                onMapClick = onMapClick,
            )
        }
    }
}

@Composable
private fun MapsTopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ValorantDark)
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        Text(
            text = "MAPS",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MapsContainer(
    state: MapsUiState,
    onRefresh: () -> Unit,
    onMapClick: (ValorantMap) -> Unit,
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

        state.maps.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No maps found.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f),
                )
            }
        }

        else -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.maps) { map ->
                        MapCard(
                            map = map,
                            onClick = { onMapClick(map) },
                        )
                    }
                }
            }
        }
    }
}

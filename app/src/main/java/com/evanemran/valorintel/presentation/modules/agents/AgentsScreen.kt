package com.evanemran.valorintel.presentation.modules.agents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.presentation.components.AgentCard
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun AgentsScreen(
    onAgentClick: (Agent) -> Unit,
    onBackClick: (() -> Unit)? = null,
    viewModel: AgentsViewModel = viewModel(factory = AgentsViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantDark),
    ) {
        AgentsTopBar(onBackClick = onBackClick)

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            AgentsContainer(
                state = state,
                onRefresh = { viewModel.loadAgents(forceFresh = true) },
                onAgentClick = onAgentClick,
            )
        }
    }
}

@Composable
private fun AgentsTopBar(onBackClick: (() -> Unit)?) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(ValorantDark)
            .padding(horizontal = 4.dp, vertical = 8.dp),
    ) {
        if (onBackClick != null) {
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.align(Alignment.CenterStart),
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = ValorantRed,
                )
            }
        }

        Text(
            text = "AGENTS",
            color = Color.White,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AgentsContainer(
    state: AgentsUiState,
    onRefresh: () -> Unit,
    onAgentClick: (Agent) -> Unit,
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

        state.agents.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = "No agents found.",
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
                    items(state.agents) { agent ->
                        AgentCard(
                            agent = agent,
                            onClick = { onAgentClick(agent) },
                        )
                    }
                }
            }
        }
    }
}

package com.evanemran.valorintel.presentation.modules.home

import android.content.ClipData
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.presentation.components.AgentCard
import com.evanemran.valorintel.presentation.theme.AgentRed
import com.evanemran.valorintel.presentation.theme.AgentRedSurface


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onAgentClick: (Agent) -> Unit,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),) {
        val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.background(AgentRed),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Valorintel",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AgentRed),
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AgentRed)
                .padding(innerPadding),
        ) {
            AgentsContainer(
                state = state,
                onRefresh = { viewModel.loadAgents(forceFresh = true) },
                onAgentClick = onAgentClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AgentsContainer(
    state: HomeUiState,
    onRefresh: () -> Unit,
    onAgentClick: (Agent) -> Unit
) {
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = AgentRedSurface)
            }
        }

        state.error != null -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth().height(480.dp), contentAlignment = Alignment.Center) {
                            Text(
                                text = "Failed to load data.\n${state.error}",
                                textAlign = TextAlign.Center,
                                color = Color.Black.copy(alpha = 0.54f),
                                modifier = Modifier.padding(24.dp),
                            )
                        }
                    }
                }
            }
        }

        state.agents.isEmpty() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No agents found.", style = MaterialTheme.typography.bodyMedium)
            }
        }

        else -> {
            PullToRefreshBox(isRefreshing = state.isRefreshing, onRefresh = onRefresh) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 4.dp),
                ) {
                    items(state.agents) { agent ->
                        AgentCard(
                            agent = agent,
                            onClick = {
                                val url = agent
                                onAgentClick(agent)
                            },
                        )
                    }
                }
            }
        }
    }
}
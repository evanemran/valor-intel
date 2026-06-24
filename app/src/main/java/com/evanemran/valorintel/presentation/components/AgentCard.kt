package com.evanemran.valorintel.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.evanemran.valorintel.core.AppStrings
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun AgentCard(
    agent: Agent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val portraitUrl = agent.fullPortraitV2?.takeIf { it.isNotBlank() }
        ?: agent.fullPortrait?.takeIf { it.isNotBlank() }
        ?: agent.bustPortrait?.takeIf { it.isNotBlank() }
        ?: agent.displayIcon?.takeIf { it.isNotBlank() }
        ?: AppStrings.PLACEHOLDER_IMG

    Column(
        modifier = modifier
            .aspectRatio(0.68f)
            .border(width = 1.dp, color = ValorantRed)
            .background(ValorantDark)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = agent.displayName?.uppercase() ?: "N/A",
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter,
        ) {
            SubcomposeAsyncImage(
                model = portraitUrl,
                contentDescription = agent.displayName,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize(),
                error = {
                    SubcomposeAsyncImage(
                        model = AppStrings.PLACEHOLDER_IMG,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize(),
                    )
                },
            )
        }
    }
}

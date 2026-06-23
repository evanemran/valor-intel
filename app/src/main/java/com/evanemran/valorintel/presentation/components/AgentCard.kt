package com.evanemran.valorintel.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.evanemran.valorintel.core.AppStrings
import com.evanemran.valorintel.core.utils.DateUtil
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.presentation.theme.AgentRed
import com.evanemran.valorintel.presentation.theme.AgentRedSurface


@Composable
fun AgentCard(
    agent: Agent,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageUrl =agent.displayIcon?.takeIf {
        it.isNotBlank()
    } ?: AppStrings.PLACEHOLDER_IMG

    Card(
        onClick = onClick,
        modifier =modifier.fillMaxWidth().padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = AgentRedSurface),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                SubcomposeAsyncImage(
                    model =imageUrl,
                    contentDescription = agent.displayName,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(100.dp),
                    error = {
                        SubcomposeAsyncImage(
                            model = AppStrings.PLACEHOLDER_IMG,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(100.dp),
                        )
                    },
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(2.dp),
                ) {
                    Text(
                        text = agent.displayName ?: "N/A",
                        color = AgentRed,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "Published: ${DateUtil.formatToHumanReadable(agent.releaseDate)}",
                        color = Color.Black.copy(alpha = 0.54f),
                        fontSize = 12.sp,
                    )
                    Text(
                        text = "Developer: ${agent.developerName ?: "Unknown"}",
                        color = Color.Black.copy(alpha = 0.54f),
                        fontSize = 12.sp,
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = agent.description ?: "N/A",
                color = Color.Black.copy(alpha = 0.54f),
                fontSize = 12.sp,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
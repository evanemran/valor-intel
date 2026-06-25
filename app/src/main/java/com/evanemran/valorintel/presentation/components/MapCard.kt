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
import com.evanemran.valorintel.domain.models.ValorantMap
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun MapCard(
    map: ValorantMap,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageUrl = map.splash?.takeIf { it.isNotBlank() }
        ?: map.stylizedBackgroundImage?.takeIf { it.isNotBlank() }
        ?: map.listViewIconTall?.takeIf { it.isNotBlank() }
        ?: map.displayIcon?.takeIf { it.isNotBlank() }
        ?: AppStrings.PLACEHOLDER_IMG

    Column(
        modifier = modifier
            .aspectRatio(1.6f)
            .border(width = 1.dp, color = ValorantRed)
            .background(ValorantDark)
            .clickable(onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = map.displayName,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                error = {
                    SubcomposeAsyncImage(
                        model = AppStrings.PLACEHOLDER_IMG,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize(),
                    )
                },
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Black.copy(alpha = 0.55f))
                    .padding(horizontal = 12.dp, vertical = 10.dp),
            ) {
                Column {
                    Text(
                        text = map.displayName?.uppercase() ?: "N/A",
                        color = Color.White,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    map.tacticalDescription?.takeIf { it.isNotBlank() }?.let { description ->
                        Text(
                            text = description.uppercase(),
                            color = ValorantRed,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

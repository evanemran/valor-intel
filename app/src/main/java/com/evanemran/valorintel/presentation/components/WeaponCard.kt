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
import com.evanemran.valorintel.domain.models.Weapon
import com.evanemran.valorintel.presentation.modules.weapons.WeaponCategoryUtil.toDisplayCategory
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

@Composable
fun WeaponCard(
    weapon: Weapon,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val imageUrl = weapon.shopData?.newImage?.takeIf { it.isNotBlank() }
        ?: weapon.displayIcon?.takeIf { it.isNotBlank() }
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
            text = weapon.displayName?.uppercase() ?: "N/A",
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
            contentAlignment = Alignment.Center,
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = weapon.displayName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = weapon.category.toDisplayCategory()?.uppercase() ?: "",
                color = ValorantRed,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            weapon.shopData?.cost?.let { cost ->
                Text(
                    text = "₡ $cost",
                    color = Color.White.copy(alpha = 0.7f),
                    style = MaterialTheme.typography.labelSmall,
                )
            }
        }
    }
}


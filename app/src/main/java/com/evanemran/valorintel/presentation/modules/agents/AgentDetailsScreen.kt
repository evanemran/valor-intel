package com.evanemran.valorintel.presentation.modules.agents

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import com.evanemran.valorintel.core.utils.DateUtil
import com.evanemran.valorintel.domain.models.Ability
import com.evanemran.valorintel.domain.models.Agent
import com.evanemran.valorintel.domain.models.RecruitmentData
import com.evanemran.valorintel.presentation.theme.ValorantDark
import com.evanemran.valorintel.presentation.theme.ValorantRed

private val CardSurface = Color(0xFF1A2634)
private val MutedText = Color.White.copy(alpha = 0.6f)

@Composable
fun AgentDetailsScreen(
    agentUuid: String,
    onBackClick: () -> Unit,
    viewModel: AgentDetailsViewModel = viewModel(factory = AgentDetailsViewModel.factory(agentUuid)),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val agent = state.agent
    val error = state.error

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ValorantDark),
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    color = ValorantRed,
                    modifier = Modifier.align(Alignment.Center),
                )
            }

            error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = error,
                        color = MutedText,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tap back to return",
                        color = ValorantRed,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }

            agent != null -> {
                AgentDetailsContent(agent = agent)
            }
        }

        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.TopStart)
                .background(Color.Black.copy(alpha = 0.35f), CircleShape),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun AgentDetailsContent(agent: Agent) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 32.dp),
    ) {
        item { AgentHeroSection(agent) }
        item { Spacer(modifier = Modifier.height(20.dp)) }

        agent.description?.takeIf { it.isNotBlank() }?.let { description ->
            item {
                SectionTitle("BIOGRAPHY")
                AccentCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Text(
                        text = description,
                        color = Color.White.copy(alpha = 0.9f),
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    )
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }

        agent.role?.let { role ->
            item {
                SectionTitle("ROLE")
                AccentCard(modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        role.displayIcon?.let { iconUrl ->
                            SubcomposeAsyncImage(
                                model = iconUrl,
                                contentDescription = role.displayName,
                                modifier = Modifier.size(40.dp),
                                contentScale = ContentScale.Fit,
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                        Column {
                            Text(
                                text = role.displayName?.uppercase() ?: "N/A",
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                            )
                            role.description?.let { desc ->
                                Text(
                                    text = desc,
                                    color = MutedText,
                                    style = MaterialTheme.typography.bodySmall,
                                    modifier = Modifier.padding(top = 4.dp),
                                )
                            }
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }

        val abilities = agent.abilities?.filterNotNull().orEmpty()
        if (abilities.isNotEmpty()) {
            item { SectionTitle("ABILITIES") }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(abilities) { ability ->
                        AbilityCard(ability)
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(24.dp)) }
        }

        item {
            SectionTitle("AGENT INTEL")
            AgentIntelSection(agent)
        }

        agent.recruitmentData?.let { recruitment ->
            item { Spacer(modifier = Modifier.height(24.dp)) }
            item {
                SectionTitle("RECRUITMENT")
                RecruitmentSection(recruitment)
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }
        item {
            SectionTitle("ASSETS")
            AssetsSection(agent)
        }
    }
}

@Composable
private fun AgentHeroSection(agent: Agent) {
    val portraitUrl = agent.fullPortraitV2 ?: agent.fullPortrait ?: agent.bustPortrait
    val backgroundUrl = agent.background

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(360.dp),
    ) {
        if (!backgroundUrl.isNullOrBlank()) {
            SubcomposeAsyncImage(
                model = backgroundUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxSize(),
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(ValorantRed.copy(alpha = 0.35f), ValorantDark),
                        ),
                    ),
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            ValorantDark.copy(alpha = 0.4f),
                            ValorantDark,
                        ),
                    ),
                ),
        )

        if (!portraitUrl.isNullOrBlank()) {
            SubcomposeAsyncImage(
                model = portraitUrl,
                contentDescription = agent.displayName,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 48.dp, bottom = 16.dp),
                alignment = Alignment.BottomCenter,
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 20.dp, vertical = 16.dp),
        ) {
            agent.role?.displayName?.let { roleName ->
                Text(
                    text = roleName.uppercase(),
                    color = ValorantRed,
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                )
            }
            Text(
                text = agent.displayName?.uppercase() ?: "UNKNOWN",
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
            )
            agent.developerName?.let { devName ->
                Text(
                    text = devName,
                    color = MutedText,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 2.dp),
                )
            }
        }
    }
}

@Composable
private fun SectionTitle(title: String) {
    Text(
        text = title,
        color = ValorantRed,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
    )
}

@Composable
private fun AccentCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp)),
    ) {
        Box(
            modifier = Modifier
                .width(4.dp)
                .fillMaxHeight()
                .background(ValorantRed),
        )
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@Composable
private fun AbilityCard(ability: Ability) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ability.slot?.let { slot ->
            Text(
                text = slot.cleanSlotName().uppercase(),
                color = ValorantRed,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        ability.displayIcon?.let { icon ->
            SubcomposeAsyncImage(
                model = icon,
                contentDescription = ability.displayName,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Fit,
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        Text(
            text = ability.displayName ?: "Ability",
            color = Color.White,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
        )
        ability.description?.let { desc ->
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = desc,
                color = MutedText,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 4,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AgentIntelSection(agent: Agent) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        InfoGrid(
            items = buildList {
                agent.releaseDate?.let { add("Released" to DateUtil.formatToHumanReadable(it)) }
                agent.uuid?.let { add("UUID" to it) }
                agent.isPlayableCharacter?.let { add("Playable" to it.toDisplayString()) }
                agent.isBaseContent?.let { add("Base Content" to it.toDisplayString()) }
                agent.isAvailableForTest?.let { add("Test Server" to it.toDisplayString()) }
                agent.isFullPortraitRightFacing?.let { add("Right Facing" to it.toDisplayString()) }
            },
        )

        val tags = agent.characterTags?.filterNotNull().orEmpty()
        if (tags.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "CHARACTER TAGS",
                color = MutedText,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                tags.forEach { tag ->
                    TagChip(tag)
                }
            }
        }

        agent.backgroundGradientColors?.filterNotNull()?.takeIf { it.isNotEmpty() }?.let { colors ->
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "GRADIENT COLORS",
                color = MutedText,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                colors.forEach { hex ->
                    TagChip(hex)
                }
            }
        }
    }
}

@Composable
private fun RecruitmentSection(recruitment: RecruitmentData) {
    InfoGrid(
        modifier = Modifier.padding(horizontal = 16.dp),
        items = buildList {
            recruitment.startDate?.let { add("Start" to DateUtil.formatToHumanReadable(it)) }
            recruitment.endDate?.let { add("End" to DateUtil.formatToHumanReadable(it)) }
            recruitment.milestoneThreshold?.let { add("Milestone Threshold" to it.toString()) }
            recruitment.levelVpCostOverride?.let { add("VP Cost Override" to it.toString()) }
            recruitment.useLevelVpCostOverride?.let { add("Use VP Override" to it.toDisplayString()) }
            recruitment.counterId?.let { add("Counter ID" to it) }
            recruitment.milestoneId?.let { add("Milestone ID" to it) }
        },
    )
}

@Composable
private fun AssetsSection(agent: Agent) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        AssetPreviewRow(label = "Display Icon", url = agent.displayIcon)
        AssetPreviewRow(label = "Killfeed", url = agent.killfeedPortrait)
        AssetPreviewRow(label = "Minimap", url = agent.minimapPortrait)
        AssetPreviewRow(label = "Promo Tile", url = agent.homeScreenPromoTileImage)
        agent.voiceLine?.takeIf { it.isNotBlank() }?.let { voiceLine ->
            InfoRow(label = "Voice Line", value = voiceLine)
        }
        agent.assetPath?.let { InfoRow(label = "Asset Path", value = it) }
        agent.role?.assetPath?.let { InfoRow(label = "Role Asset", value = it) }
    }
}

@Composable
private fun AssetPreviewRow(label: String, url: String?) {
    if (url.isNullOrBlank()) return
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .padding(12.dp),
    ) {
        Text(
            text = label.uppercase(),
            color = ValorantRed,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(8.dp))
        SubcomposeAsyncImage(
            model = url,
            contentDescription = label,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
        )
    }
}

@Composable
private fun InfoGrid(
    items: List<Pair<String, String>>,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) return
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(CardSurface)
            .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(12.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items.forEach { (label, value) ->
            InfoRow(label = label, value = value)
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column {
        Text(
            text = label.uppercase(),
            color = MutedText,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 2.dp),
        )
    }
}

@Composable
private fun TagChip(text: String) {
    Text(
        text = text,
        color = Color.White,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(ValorantRed.copy(alpha = 0.2f))
            .border(1.dp, ValorantRed.copy(alpha = 0.5f), RoundedCornerShape(6.dp))
            .padding(horizontal = 10.dp, vertical = 4.dp),
    )
}

private fun Boolean.toDisplayString(): String = if (this) "Yes" else "No"

private fun String.cleanSlotName(): String =
  substringAfter("::").replace(Regex("([a-z])([A-Z])"), "$1 $2")

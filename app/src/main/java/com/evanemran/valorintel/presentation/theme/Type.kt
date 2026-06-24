package com.evanemran.valorintel.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.evanemran.valorintel.R

// Custom app font. File located at: app/src/main/res/font/valorant_font.ttf
val CustomFontFamily = FontFamily(
    Font(R.font.valorant_font)
)

private val DefaultTypography = Typography()

// Apply the custom font to every Material typography style so the whole app uses it.
val Typography = Typography(
    displayLarge = DefaultTypography.displayLarge.copy(fontFamily = CustomFontFamily),
    displayMedium = DefaultTypography.displayMedium.copy(fontFamily = CustomFontFamily),
    displaySmall = DefaultTypography.displaySmall.copy(fontFamily = CustomFontFamily),
    headlineLarge = DefaultTypography.headlineLarge.copy(fontFamily = CustomFontFamily),
    headlineMedium = DefaultTypography.headlineMedium.copy(fontFamily = CustomFontFamily),
    headlineSmall = DefaultTypography.headlineSmall.copy(fontFamily = CustomFontFamily),
    titleLarge = DefaultTypography.titleLarge.copy(fontFamily = CustomFontFamily),
    titleMedium = DefaultTypography.titleMedium.copy(fontFamily = CustomFontFamily),
    titleSmall = DefaultTypography.titleSmall.copy(fontFamily = CustomFontFamily),
    bodyLarge = DefaultTypography.bodyLarge.copy(fontFamily = CustomFontFamily),
    bodyMedium = DefaultTypography.bodyMedium.copy(fontFamily = CustomFontFamily),
    bodySmall = DefaultTypography.bodySmall.copy(fontFamily = CustomFontFamily),
    labelLarge = DefaultTypography.labelLarge.copy(fontFamily = CustomFontFamily),
    labelMedium = DefaultTypography.labelMedium.copy(fontFamily = CustomFontFamily),
    labelSmall = DefaultTypography.labelSmall.copy(fontFamily = CustomFontFamily),
)

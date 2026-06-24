package com.evanemran.valorintel.domain.models

data class ValorantMap(
    val uuid: String? = null,
    val displayName: String? = null,
    val narrativeDescription: String? = null,
    val tacticalDescription: String? = null,
    val coordinates: String? = null,
    val displayIcon: String? = null,
    val listViewIcon: String? = null,
    val listViewIconTall: String? = null,
    val splash: String? = null,
    val stylizedBackgroundImage: String? = null,
    val premierBackgroundImage: String? = null,
    val assetPath: String? = null,
    val mapUrl: String? = null,
    val xMultiplier: Double? = null,
    val yMultiplier: Double? = null,
    val xScalarToAdd: Double? = null,
    val yScalarToAdd: Double? = null,
    val callouts: List<Callout> = emptyList()
)

data class Callout(
    val regionName: String? = null,
    val superRegion: String? = null,
    val superRegionName: String? = null,
    val location: Location? = null,
    val scale3D: Location? = null,
    val rotation: Rotation? = null
)

data class Location(
    val x: Double? = null,
    val y: Double? = null,
    val z: Double? = null
)

data class Rotation(
    val pitch: Double? = null,
    val yaw: Double? = null,
    val roll: Double? = null
)

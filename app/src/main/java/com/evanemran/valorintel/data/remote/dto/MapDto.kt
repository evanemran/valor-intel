package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Callout
import com.evanemran.valorintel.domain.models.Location
import com.evanemran.valorintel.domain.models.Rotation
import com.evanemran.valorintel.domain.models.ValorantMap
import com.google.gson.annotations.SerializedName

data class MapDto(
    @SerializedName("uuid") val uuid: String? = null,
    @SerializedName("displayName") val displayName: String? = null,
    @SerializedName("narrativeDescription") val narrativeDescription: String? = null,
    @SerializedName("tacticalDescription") val tacticalDescription: String? = null,
    @SerializedName("coordinates") val coordinates: String? = null,
    @SerializedName("displayIcon") val displayIcon: String? = null,
    @SerializedName("listViewIcon") val listViewIcon: String? = null,
    @SerializedName("listViewIconTall") val listViewIconTall: String? = null,
    @SerializedName("splash") val splash: String? = null,
    @SerializedName("stylizedBackgroundImage") val stylizedBackgroundImage: String? = null,
    @SerializedName("premierBackgroundImage") val premierBackgroundImage: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null,
    @SerializedName("mapUrl") val mapUrl: String? = null,
    @SerializedName("xMultiplier") val xMultiplier: Double? = null,
    @SerializedName("yMultiplier") val yMultiplier: Double? = null,
    @SerializedName("xScalarToAdd") val xScalarToAdd: Double? = null,
    @SerializedName("yScalarToAdd") val yScalarToAdd: Double? = null,
    @SerializedName("callouts") val callouts: List<CalloutDto?>? = null
)

data class CalloutDto(
    @SerializedName("regionName") val regionName: String? = null,
    @SerializedName("superRegion") val superRegion: String? = null,
    @SerializedName("superRegionName") val superRegionName: String? = null,
    @SerializedName("location") val location: LocationDto? = null,
    @SerializedName("scale3D") val scale3D: LocationDto? = null,
    @SerializedName("rotation") val rotation: RotationDto? = null
)

data class LocationDto(
    @SerializedName("x") val x: Double? = null,
    @SerializedName("y") val y: Double? = null,
    @SerializedName("z") val z: Double? = null
)

data class RotationDto(
    @SerializedName("pitch") val pitch: Double? = null,
    @SerializedName("yaw") val yaw: Double? = null,
    @SerializedName("roll") val roll: Double? = null
)

fun MapDto.toDomain(): ValorantMap = ValorantMap(
    uuid = uuid,
    displayName = displayName,
    narrativeDescription = narrativeDescription,
    tacticalDescription = tacticalDescription,
    coordinates = coordinates,
    displayIcon = displayIcon,
    listViewIcon = listViewIcon,
    listViewIconTall = listViewIconTall,
    splash = splash,
    stylizedBackgroundImage = stylizedBackgroundImage,
    premierBackgroundImage = premierBackgroundImage,
    assetPath = assetPath,
    mapUrl = mapUrl,
    xMultiplier = xMultiplier,
    yMultiplier = yMultiplier,
    xScalarToAdd = xScalarToAdd,
    yScalarToAdd = yScalarToAdd,
    callouts = callouts?.filterNotNull()?.map { it.toDomain() } ?: emptyList()
)

fun CalloutDto.toDomain(): Callout = Callout(
    regionName = regionName,
    superRegion = superRegion,
    superRegionName = superRegionName,
    location = location?.toDomain(),
    scale3D = scale3D?.toDomain(),
    rotation = rotation?.toDomain()
)

fun LocationDto.toDomain(): Location = Location(
    x = x,
    y = y,
    z = z
)

fun RotationDto.toDomain(): Rotation = Rotation(
    pitch = pitch,
    yaw = yaw,
    roll = roll
)

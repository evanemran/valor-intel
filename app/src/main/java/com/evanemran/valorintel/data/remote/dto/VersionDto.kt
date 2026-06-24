package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.Version
import com.google.gson.annotations.SerializedName

data class VersionDto(
    @SerializedName("manifestId") val manifestId: String? = null,
    @SerializedName("branch") val branch: String? = null,
    @SerializedName("version") val version: String? = null,
    @SerializedName("buildVersion") val buildVersion: String? = null,
    @SerializedName("engineVersion") val engineVersion: String? = null,
    @SerializedName("riotClientVersion") val riotClientVersion: String? = null,
    @SerializedName("riotClientBuild") val riotClientBuild: String? = null,
    @SerializedName("buildDate") val buildDate: String? = null
)

fun VersionDto.toDomain(): Version = Version(
    manifestId = manifestId,
    branch = branch,
    version = version,
    buildVersion = buildVersion,
    engineVersion = engineVersion,
    riotClientVersion = riotClientVersion,
    riotClientBuild = riotClientBuild,
    buildDate = buildDate
)

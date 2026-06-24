package com.evanemran.valorintel.domain.models

data class Version(
    val manifestId: String? = null,
    val branch: String? = null,
    val version: String? = null,
    val buildVersion: String? = null,
    val engineVersion: String? = null,
    val riotClientVersion: String? = null,
    val riotClientBuild: String? = null,
    val buildDate: String? = null
)

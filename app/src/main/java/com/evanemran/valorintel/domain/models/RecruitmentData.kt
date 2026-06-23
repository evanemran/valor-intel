package com.evanemran.valorintel.domain.models

data class RecruitmentData(
    val counterId: String? = null,
    val endDate: String? = null,
    val levelVpCostOverride: Int? = null,
    val milestoneId: String? = null,
    val milestoneThreshold: Int? = null,
    val startDate: String? = null,
    val useLevelVpCostOverride: Boolean? = null
)
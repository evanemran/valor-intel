package com.evanemran.valorintel.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Generic envelope returned by every valorant-api.com endpoint.
 * `data` is a [List] for collection endpoints and a single object for "by uuid" endpoints.
 */
data class ApiResponseDto<T>(
    @SerializedName("status") val status: Int? = null,
    @SerializedName("data") val `data`: T? = null
)

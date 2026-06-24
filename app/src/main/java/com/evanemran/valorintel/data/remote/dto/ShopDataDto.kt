package com.evanemran.valorintel.data.remote.dto

import com.evanemran.valorintel.domain.models.GridPosition
import com.evanemran.valorintel.domain.models.ShopData
import com.google.gson.annotations.SerializedName

data class ShopDataDto(
    @SerializedName("cost") val cost: Int? = null,
    @SerializedName("category") val category: String? = null,
    @SerializedName("shopOrderPriority") val shopOrderPriority: Int? = null,
    @SerializedName("categoryText") val categoryText: String? = null,
    @SerializedName("gridPosition") val gridPosition: GridPositionDto? = null,
    @SerializedName("canBeTrashed") val canBeTrashed: Boolean? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("newImage") val newImage: String? = null,
    @SerializedName("newImage2") val newImage2: String? = null,
    @SerializedName("assetPath") val assetPath: String? = null
)

data class GridPositionDto(
    @SerializedName("row") val row: Int? = null,
    @SerializedName("column") val column: Int? = null
)

fun ShopDataDto.toDomain(): ShopData = ShopData(
    cost = cost,
    category = category,
    shopOrderPriority = shopOrderPriority,
    categoryText = categoryText,
    gridPosition = gridPosition?.toDomain(),
    canBeTrashed = canBeTrashed,
    image = image,
    newImage = newImage,
    newImage2 = newImage2,
    assetPath = assetPath
)

fun GridPositionDto.toDomain(): GridPosition = GridPosition(
    row = row,
    column = column
)

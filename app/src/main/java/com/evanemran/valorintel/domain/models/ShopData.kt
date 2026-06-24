package com.evanemran.valorintel.domain.models

data class ShopData(
    val cost: Int? = null,
    val category: String? = null,
    val shopOrderPriority: Int? = null,
    val categoryText: String? = null,
    val gridPosition: GridPosition? = null,
    val canBeTrashed: Boolean? = null,
    val image: String? = null,
    val newImage: String? = null,
    val newImage2: String? = null,
    val assetPath: String? = null
)

data class GridPosition(
    val row: Int? = null,
    val column: Int? = null
)

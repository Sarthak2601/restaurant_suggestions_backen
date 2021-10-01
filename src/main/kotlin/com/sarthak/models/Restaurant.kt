package com.sarthak.models

data class Restaurant(
    override val id: String,
    var userId: String,
    var name: String,
    var description: String,
    var imageUrl: String
) : Model

data class RestaurantInput(
    val name: String,
    val description: String,
    val imageUrl: String
)

data class PagingInfo(
    var count: Int,
    var pages: Int,
    var next: Int?,
    var prev: Int?
)

data class RestaurantPage(
    val results: List<Restaurant>,
    val info: PagingInfo
)

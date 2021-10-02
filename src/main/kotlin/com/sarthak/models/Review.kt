package com.sarthak.models

data class Review(
    override val id: String,
    val userId: String,
    val restaurantId: String,
    val text: String,
    val rating: Int
): Model

data class ReviewInput(
    val text: String,
    val rating: Int
)

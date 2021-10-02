package com.sarthak.plugins

import com.apurebase.kgraphql.GraphQL
import com.sarthak.graphql.restaurantSchema
import com.sarthak.graphql.reviewSchema
import com.sarthak.service.RestaurantService
import com.sarthak.service.ReviewService
import io.ktor.application.*

fun Application.configureGraphQl() {
    install(GraphQL) {
        val restaurantService = RestaurantService()
        val reviewService = ReviewService()
        playground = true
        schema {
            restaurantSchema(restaurantService)
            reviewSchema(reviewService)
        }
    }
}

package com.sarthak.plugins

import com.apurebase.kgraphql.GraphQL
import com.sarthak.graphql.restaurantSchema
import com.sarthak.service.RestaurantService
import io.ktor.application.*

fun Application.configureGraphQl() {
    install(GraphQL) {
        val restaurantService = RestaurantService()
        playground = true
        schema {
            restaurantSchema(restaurantService)
        }
    }
}

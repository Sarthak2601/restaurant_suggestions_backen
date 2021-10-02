package com.sarthak.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.sarthak.models.Restaurant
import com.sarthak.models.RestaurantInput
import com.sarthak.service.RestaurantService

fun SchemaBuilder.restaurantSchema(restaurantService: RestaurantService){
    inputType<RestaurantInput>{
        description = "The input of the restaurant without identifier"
    }

    type<Restaurant>{
        description = "restaurant object with attributes name, description and imageUrl"
    }

    query("restaurant") {
        resolver { restaurantId: String ->
            try {
                restaurantService.getRestaurant(restaurantId)
            }catch (e: Exception){
                null
            }
        }
    }

    query("restaurants"){
        resolver{ page: Int?, size: Int? ->
            try {
                restaurantService.getRestaurantsPage(page?: 0, size?: 10 )
            }catch (e: Exception){
                null
            }
        }
    }

    mutation("createRestaurant"){
        description = "Create a new restaurant"
        resolver { restaurantInput: RestaurantInput ->
            try {
                val userId = "abc"
                restaurantService.createRestaurant(restaurantInput, userId)
            }catch (e: Exception){
                throw e
            }
        }
    }

    mutation("deleteRestaurant"){
        description = "Delete a restaurant"
        resolver { restaurantId: String ->
            try {
                val userId = "abc"
                restaurantService.deleteRestaurant(userId, restaurantId)
            }catch (e: Exception){
                null
            }
        }
    }

    mutation("updateRestaurant"){
        description = "Update the existing restaurants"
        resolver { restaurantId: String, restaurantInput: RestaurantInput ->
            try {
                val userId = "abc"
                restaurantService.updateRestaurant(restaurantId, userId, restaurantInput)
            }catch (e: Exception){
                null
            }
        }
    }
}
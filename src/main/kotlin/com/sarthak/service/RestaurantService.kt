package com.sarthak.service

import com.mongodb.client.MongoClient
import com.sarthak.models.Restaurant
import com.sarthak.models.RestaurantInput
import com.sarthak.models.RestaurantPage
import com.sarthak.repository.RestaurantRepository
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class RestaurantService: KoinComponent {
    private val client: MongoClient by inject()
    private val repository = RestaurantRepository(client)

    fun getRestaurant(id: String): Restaurant {
        return repository.getById(id)
    }

    fun createRestaurant(restaurantInput: RestaurantInput, userId: String): Restaurant {
        val uid = UUID.randomUUID()
        val restaurant =
            Restaurant(uid.toString(), userId, restaurantInput.name, restaurantInput.description, restaurantInput.imageUrl)
        return repository.add(restaurant)
    }

    fun updateRestaurant(restaurantId: String, userId: String, restaurantInput: RestaurantInput): Restaurant {
        val dessert = repository.getById(restaurantId)
        if (dessert.userId == userId) {
            val update = Restaurant(restaurantId, userId, restaurantInput.name, restaurantInput.description, restaurantInput.imageUrl)
            repository.update(update)
        }
        error("Can't update restaurant")
    }

    fun deleteRestaurant(userId: String, restaurantId: String): Boolean {
        val restaurant = repository.getById(restaurantId)
        if (restaurant.userId == userId) {
            return repository.delete(restaurantId)
        }
        error("Can't delete restaurant")
    }

    fun getRestaurantsPage(page: Int, size: Int): RestaurantPage {
        return repository.getRestaurantsPage(page, size)
    }

}
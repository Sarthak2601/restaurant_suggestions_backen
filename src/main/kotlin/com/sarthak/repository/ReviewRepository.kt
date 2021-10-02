package com.sarthak.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.sarthak.models.Review
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

class ReviewRepository(client: MongoClient): Repository<Review> {
    override lateinit var collection: MongoCollection<Review>
    init {
        val database = client.getDatabase("backend")
        collection = database.getCollection<Review>("Review")
    }

    fun getReviewsByRestaurantId(restaurantId: String): List<Review>{
        try {
            val response = collection.find(Review::restaurantId eq restaurantId) ?: throw Exception("No reviews found")
            return response.asIterable().map { it }
        }catch (t: Throwable){
            throw Exception("Cannot find reviews")
        }
    }
}

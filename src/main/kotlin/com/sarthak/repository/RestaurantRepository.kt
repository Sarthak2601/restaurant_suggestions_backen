package com.sarthak.repository

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.sarthak.models.PagingInfo
import com.sarthak.models.Restaurant
import com.sarthak.models.RestaurantPage
import org.litote.kmongo.eq
import org.litote.kmongo.getCollection

class RestaurantRepository(client: MongoClient): Repository<Restaurant> {
    override lateinit var collection: MongoCollection<Restaurant>
    init {
        val database = client.getDatabase("backend")
        collection = database.getCollection<Restaurant>("Restaurant")
    }

    fun getRestaurantsPage(page: Int, size: Int): RestaurantPage {
        try {
            val skips = page*size
            val response = collection.find().skip(skips).limit(size)
            val results = response.asIterable().map { it }
            val totalDesserts = collection.estimatedDocumentCount()
            val totalPages = if (totalDesserts.toInt()%size==0) totalDesserts/size else (totalDesserts/size) +1
            val next = if ((page+1)*size < totalDesserts.toInt()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(totalDesserts.toInt(), totalPages.toInt(), next, prev)
            return RestaurantPage(results, info)
        }catch (t: Throwable){
            throw Exception("Can't get desserts page")
        }
    }

    fun getRestaurantsByUserId(userId: String): List<Restaurant>{
        return try {
            collection.find(Restaurant::userId eq userId).asIterable().map { it }
        }catch (t: Throwable){
            throw Exception("Can't get user restaurants")
        }
    }
}

package com.sarthak.graphql

import com.apurebase.kgraphql.schema.dsl.SchemaBuilder
import com.sarthak.models.*
import com.sarthak.service.ReviewService

fun SchemaBuilder.reviewSchema(reviewService: ReviewService){
    inputType<ReviewInput>{
        description = "The input of the review without identifier"
    }

    type<Review>{
        description = "Review object with attributes text and rating"
    }

    query("review") {
        resolver { reviewId: String ->
            try {
                reviewService.getReview(reviewId)
            }catch (e: Exception){
                null
            }
        }
    }

    mutation("createReview"){
        description = "Create a new review"
        resolver { reviewInput: ReviewInput, restaurantId: String ->
            try {
                val userId = "abc"
                reviewService.createReview(reviewInput, userId, restaurantId)
            }catch (e: Exception){
                throw e
            }
        }
    }

    mutation("deleteReview"){
        description = "Delete a review"
        resolver { reviewId: String ->
            try {
                val userId = "abc"
                reviewService.deleteReview(userId, reviewId)
            }catch (e: Exception){
                null
            }
        }
    }

    mutation("updateReview"){
        description = "Update the existing reviews"
        resolver { reviewId: String, reviewInput: ReviewInput ->
            try {
                val userId = "abc"
                reviewService.updateReview(reviewId, userId, reviewInput)
            }catch (e: Exception){
                null
            }
        }
    }
}

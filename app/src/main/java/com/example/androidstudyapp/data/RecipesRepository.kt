package com.example.androidstudyapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class RecipesRepository {
    private val retrofit = Retrofit.Builder()
        .baseUrl("$API_BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val recipeApiService: RecipeApiService = retrofit.create(RecipeApiService::class.java)

    suspend fun getCategories(): List<Category>? {
        return try {
            recipeApiService.getListCategory().execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRecipesByCategoryId(categoryId: Int): List<Recipe>? {
        return try {
            recipeApiService.getListRecipesByIdCategory(categoryId).execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRecipeById(id: Int): Recipe? {
        return try {
            recipeApiService.getRecipeById(id).execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getRecipesByIds(listIdFavourites: List<Int>): List<Recipe>? {
        return try {
            recipeApiService.getListRecipesById(listIdFavourites).execute().body()
        } catch (e: IOException) {
            null
        }
    }

    suspend fun getCategoryById(id: Int): Category? {
        return try {
            recipeApiService.getCategoryById(id).execute().body()
        } catch (e: IOException) {
            null
        }
    }
}

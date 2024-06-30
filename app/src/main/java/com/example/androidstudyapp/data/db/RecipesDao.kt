package com.example.androidstudyapp.data.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidstudyapp.data.Recipe

interface RecipesDao {
    @Query("SELECT * FROM `table - list_recipe`")
    fun getListAllRecipes(): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRecipeToList(category: List<Recipe>)
}



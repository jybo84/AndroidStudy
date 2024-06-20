package com.example.androidstudyapp.ui.recipe.listRecipe

import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidstudyapp.data.Category
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository

class RecipesListViewModel(application: Application) : AndroidViewModel(application) {
    data class RecipeListState(
        var categoryName: String? = null,
        var categoryImage: Drawable? = null,
        val recipes: List<Recipe>? = emptyList(),
    )

    private val _state = MutableLiveData(RecipeListState())
    val state: LiveData<RecipeListState> = _state

    private val recipeRepository = RecipesRepository()

    fun loadRecipes(categoryId: Int) {
        _state.value = RecipeListState(
            categoryName = getCategoryById(categoryId)?.title,
            categoryImage = loadImageCategory(categoryId),
            recipes = recipeRepository.getRecipesByCategoryId(categoryId)
        )
    }

    private fun loadImageCategory(categoryId: Int): Drawable? {
        try {
            val ims = getCategoryById(categoryId)?.imageUrl?.let {
                getApplication<Application>().assets.open(it)
            }
            val picture = Drawable.createFromStream(ims, null)
            return picture

        } catch (ex: Exception) {
            Log.e("mylog", "Error: $ex")
            return null
        }
    }

    private fun getCategoryById(id: Int): Category? {
        return recipeRepository.getCategories()?.find { it.id == id }
    }
}
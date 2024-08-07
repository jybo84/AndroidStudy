package com.example.androidstudyapp.ui.recipe.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipesRepository: RecipesRepository
) : ViewModel() {

    private val _state = MutableLiveData(RecipeState())
    val state: LiveData<RecipeState> = _state

    data class RecipeState(
        val recipe: Recipe? = null,
        val isFavourite: Boolean = false,
        var portionsCount: Int = 1,
        val recipeImageUrl: String? = null,
    )

    fun loadRecipe(recipeId: Int) {
        viewModelScope.launch {

            val recipeFromNetwork = recipesRepository.getRecipeById(recipeId)
            _state.postValue(
                RecipeState(
                    recipe = recipeFromNetwork,
                    isFavourite = getFavorites().contains(recipeId),
                    portionsCount = state.value?.portionsCount ?: 1,
                    recipeImageUrl = recipeFromNetwork?.imageUrl?.let {
                        ImageUtils.getImageFullUrl(
                            it
                        )
                    }
                )
            )
        }
    }

    private suspend fun getFavorites(): List<Int> {
        return recipesRepository.getListFavouriteRecipes().map { it.id }
    }

    fun onFavoritesClicked() {
        viewModelScope.launch {
            val favourite = _state.value?.isFavourite ?: false
            _state.value?.recipe?.let { recipesRepository.updateRecipe(it.copy(isFavorite = !favourite)) }
            _state.value = _state.value?.copy(isFavourite = !favourite)
        }
    }

    fun setCountPortions(count: Int): Int? {
        state.value?.portionsCount = count
        return state.value?.portionsCount
    }
}
package com.example.androidstudyapp.ui.recipe.favourites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.data.RecipesRepository
import kotlinx.coroutines.launch

class FavouritesViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipesRepository()

    data class FavouritesState(
        val dataSet: List<Recipe>? = emptyList()
    )

    private val _state = MutableLiveData(FavouritesState())
    val state: LiveData<FavouritesState> = _state

    fun loadFavourites() {
        viewModelScope.launch {
            _state.postValue(
                FavouritesState(
                    dataSet = recipeRepository.getListFavouriteRecipes()

                )
            )
        }
    }
}

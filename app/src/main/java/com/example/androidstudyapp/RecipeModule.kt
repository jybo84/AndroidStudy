package com.example.androidstudyapp

import com.example.androidstudyapp.data.API_BASE_URL
import com.example.androidstudyapp.data.RecipeApiService
import com.example.androidstudyapp.data.db.CategoriesDao
import com.example.androidstudyapp.data.db.DataBase
import com.example.androidstudyapp.data.db.RecipesDao
import com.example.androidstudyapp.ui.RecipesApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RecipeModule() {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("$API_BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDataBase(): DataBase = DataBase.getDataBase(RecipesApplication.instance)

    @Provides
    @Singleton
    fun provideRecipeApiServer(retrofit: Retrofit): RecipeApiService = retrofit.create()

    @Provides
    @Singleton
    fun provideCategoriesDao(dataBase: DataBase): CategoriesDao = dataBase.getCategoryDao()

    @Provides
    @Singleton
    fun provideRecipesDao(dataBase: DataBase): RecipesDao = dataBase.getRecipesDao()
}
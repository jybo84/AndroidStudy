package com.example.androidstudyapp

import android.content.Context
import com.example.androidstudyapp.data.API_BASE_URL
import com.example.androidstudyapp.data.RecipeApiService
import com.example.androidstudyapp.data.db.CategoriesDao
import com.example.androidstudyapp.data.db.DataBase
import com.example.androidstudyapp.data.db.RecipesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class RecipeModule() {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("$API_BASE_URL/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideDataBase(@ApplicationContext context: Context): DataBase =
        DataBase.getDataBase(context)

    @Provides
    fun provideRecipeApiServer(retrofit: Retrofit): RecipeApiService = retrofit.create()

    @Provides
    fun provideCategoriesDao(dataBase: DataBase): CategoriesDao = dataBase.getCategoryDao()

    @Provides
    fun provideRecipesDao(dataBase: DataBase): RecipesDao = dataBase.getRecipesDao()
}

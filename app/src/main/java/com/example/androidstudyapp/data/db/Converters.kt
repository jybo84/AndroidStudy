package com.example.androidstudyapp.data.db

import androidx.room.TypeConverter
import com.example.androidstudyapp.data.Ingredient
import com.google.gson.Gson

class Converters {

    @TypeConverter
    fun listToJsonStringIngredient(value: List<Ingredient>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToListIngredient(value: String) = Gson().fromJson(value, Array<Ingredient>::class.java).toList()

    @TypeConverter
    fun listToJsonString(value: List<String>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}

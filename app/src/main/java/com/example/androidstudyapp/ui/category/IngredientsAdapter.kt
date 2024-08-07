package com.example.androidstudyapp.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidstudyapp.data.Ingredient
import com.example.androidstudyapp.databinding.ItemIngredientBinding
import java.text.DecimalFormat

class IngredientsAdapter(var dataSet: List<Ingredient> = listOf()) :
    RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        val nameOfIngredient = binding.nameOfIngredient
        val quantityIngredient = binding.quantityIngredients
        val unitOfMeasureIngredients = binding.unitOfMeasureIngredients
    }

    private var quantity: Int = 1
    private val quantityFormat = DecimalFormat("#.#")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemIngredientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient: Ingredient = dataSet[position]
        holder.nameOfIngredient.text = ingredient.description
        try {
            holder.quantityIngredient.text =
                quantityFormat.format(ingredient.quantity.toFloat() * quantity)
        } catch (e: NumberFormatException) {
            println("По вкусу")
        }

        holder.unitOfMeasureIngredients.text = ingredient.unitOfMeasure
    }

    fun updateIngredients(progress: Int) {
        quantity = progress
        notifyItemRangeChanged(0, itemCount)
    }
}

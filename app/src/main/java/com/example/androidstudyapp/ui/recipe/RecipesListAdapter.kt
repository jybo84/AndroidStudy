package com.example.androidstudyapp.ui.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidstudyapp.R
import com.example.androidstudyapp.data.ImageUtils
import com.example.androidstudyapp.data.Recipe
import com.example.androidstudyapp.databinding.ItemRecipeBinding

class RecipesListAdapter(private val dataSet: List<Recipe>) :
    RecyclerView.Adapter<RecipesListAdapter.ViewHolder>() {

    interface OnItemClickListenerRecipe {
        fun onItemClickRecipe(recipeId: Int)
    }

    private var itemClickListenerRecipe: OnItemClickListenerRecipe? = null

    fun setOnClickListenerRecipe(listenerRecipe: OnItemClickListenerRecipe) {
        itemClickListenerRecipe = listenerRecipe
    }

    class ViewHolder(binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        val ivRecipeBurgerHolder = binding.ivRecipeBurger
        val tvTitleBurgerHolder = binding.tvTitleBurger
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe: Recipe = dataSet[position]
        holder.tvTitleBurgerHolder.text = recipe.title

        Glide.with(holder.itemView)
            .load(ImageUtils.getImageFullUrl(recipe.imageUrl))
            .error(R.drawable.img_error)
            .placeholder(R.drawable.img_placeholder)
            .into(holder.ivRecipeBurgerHolder)

        holder.itemView.setOnClickListener { itemClickListenerRecipe?.onItemClickRecipe(recipe.id) }
    }

    override fun getItemCount() = dataSet.size
}
package com.venicio.makeyourmeal.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.venicio.makeyourmeal.data.model.Ingredient
import com.venicio.makeyourmeal.databinding.ItemIngredientBinding

class RecipesDetailsAdapter :
    RecyclerView.Adapter<RecipesDetailsAdapter.RecipesViewHolder>() {

    private var listIngredient = arrayListOf<Ingredient>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemIngredientBinding.inflate(inflater, parent, false)

        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bindRecipe(listIngredient[position])
    }

    override fun getItemCount(): Int = listIngredient.size

    class RecipesViewHolder(private val binding: ItemIngredientBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindRecipe(itemIngredient: Ingredient) {
            binding.cbIngredient.text =
                "${itemIngredient.measure}  ${itemIngredient.nameIngredient}"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupRecyclerIngredients(list: ArrayList<Ingredient>) {
        listIngredient = list
        notifyDataSetChanged()

    }
}
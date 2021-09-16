package com.venicio.makeyourmeal.view.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.makeyourmeal.data.model.Meals
import com.venicio.makeyourmeal.databinding.ItemMealsByCategoryBinding
import com.venicio.makeyourmeal.view.ui.MealsByCategoryFragmentDirections

class MealsByCategoryAdapter :
    RecyclerView.Adapter<MealsByCategoryAdapter.MealsViewHolder>() {


    private var listMeals = arrayListOf<Meals>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMealsByCategoryBinding.inflate(inflater, parent, false)

        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bindMealsByCategory(listMeals[position])
    }

    override fun getItemCount() = listMeals.size

    class MealsViewHolder(private val binding: ItemMealsByCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindMealsByCategory(itemMeals: Meals) {
            binding.tvTitleMeal.text = itemMeals.strMeal
            Glide.with(itemView)
                .load(itemMeals.strMealThumb)
                .into(binding.ivMealByCategory)

            binding.cvMealByCategory.setOnClickListener {
                val direction =
                    MealsByCategoryFragmentDirections.recipesFragToDetailsFrag(
                        itemMeals.idMeal,
                        itemMeals.strMeal
                    )
                binding.root.findNavController().navigate(direction)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupRecyclerMeals(mealsList: ArrayList<Meals>) {
        listMeals = mealsList
        notifyDataSetChanged()
    }
}



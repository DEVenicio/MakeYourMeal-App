package com.venicio.makeyourmeal.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.makeyourmeal.data.Meals
import com.venicio.makeyourmeal.databinding.ItemMealsByCategoryBinding

class MealsByCategoryAdapter :
    RecyclerView.Adapter<MealsByCategoryAdapter.MealsViewHolder>() {


    private var listMeals = arrayListOf<Meals>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMealsByCategoryBinding.inflate(inflater, parent, false)

        return MealsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.bind(listMeals[position])

    }

    override fun getItemCount() = listMeals.size

    class MealsViewHolder(private val binding: ItemMealsByCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(itemMeals: Meals) {
            binding.tvTitleMeal.text = itemMeals.strMeal
            Glide.with(itemView)
                .load(itemMeals.strMealThumb)
                .into(binding.ivMealByCategory)

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupRecyclerMeals(mealsList: ArrayList<Meals>){
        listMeals = mealsList
        notifyDataSetChanged()
    }
}



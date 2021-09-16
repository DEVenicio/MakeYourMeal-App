package com.venicio.makeyourmeal.view.adapter


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.makeyourmeal.data.model.CategoryFood
import com.venicio.makeyourmeal.databinding.ItemListCategoryBinding
import com.venicio.makeyourmeal.view.ui.HomeFragmentDirections

class HomeCategoryAdapter :
    RecyclerView.Adapter<HomeCategoryAdapter.CategoryViewHolder>() {

    private var listCategory = arrayListOf<CategoryFood>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCategoryBinding.inflate(inflater, parent, false)

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindCategory(listCategory[position])
    }

    override fun getItemCount() = listCategory.size


    inner class CategoryViewHolder(private val binding: ItemListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindCategory(itemCategory: CategoryFood) {
            binding.tvCategory.text = itemCategory.strCategory
            Glide.with(itemView)
                .load(itemCategory.strCategoryThumb)
                .into(binding.ivCategory)

            binding.cvCategories.setOnClickListener {
                val direction =
                    HomeFragmentDirections.homeFragToRecipesFrag(itemCategory.strCategory)
                binding.root.findNavController().navigate(direction)

            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setupRecyclerView(categoriesList: ArrayList<CategoryFood>) {
        listCategory = categoriesList
        notifyDataSetChanged()
    }
}

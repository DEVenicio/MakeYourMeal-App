package com.venicio.makeyourmeal.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.makeyourmeal.R
import com.venicio.makeyourmeal.data.CategoryResponse
import com.venicio.makeyourmeal.data.CategoryResult
import com.venicio.makeyourmeal.databinding.ItemListCategoryBinding

class CategoryAdapter(private val listCategory: List<CategoryResponse>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListCategoryBinding.inflate(inflater, parent, false)

        return CategoryViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(listCategory[position])

    }

    override fun getItemCount() = listCategory.size


    inner class CategoryViewHolder(private val binding: ItemListCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {


            fun bind(categoryResponse: CategoryResponse) {
                binding.tvCategory.text = listCategory[adapterPosition].strCategory
                Glide.with(itemView)
                    .load(listCategory[adapterPosition].strCategoryThumb)
                    .into(binding.ivCategory)



            }


    }
}

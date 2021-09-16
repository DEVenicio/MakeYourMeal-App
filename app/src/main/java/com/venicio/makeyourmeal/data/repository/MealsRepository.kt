package com.venicio.makeyourmeal.data.repository


import com.venicio.makeyourmeal.data.api.MealsService


class MealsRepository(private val service: MealsService) {


    suspend fun getAllCategories() = service.getAllCategories()

    suspend fun getMealsByCategory(category: String) = service.getMealsByCategory(category)

    suspend fun getRecipes(recipeId: String) = service.getRecipes(recipeId)


}
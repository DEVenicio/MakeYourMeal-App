package com.venicio.makeyourmeal.data.api

import com.venicio.makeyourmeal.data.model.CategoryFoodResult
import com.venicio.makeyourmeal.data.model.MealsResult
import com.venicio.makeyourmeal.data.model.RecipesResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {

    @GET("categories.php")
    suspend fun getAllCategories(): CategoryFoodResult

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): MealsResult

    @GET("lookup.php")
    suspend fun getRecipes(@Query("i") recipeId: String): RecipesResult
}
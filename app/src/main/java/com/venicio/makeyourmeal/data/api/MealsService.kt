package com.venicio.makeyourmeal.data.api

import com.venicio.makeyourmeal.data.CategoryFoodResult
import com.venicio.makeyourmeal.data.MealsResult
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {

    @GET("categories.php")
  suspend  fun getAllCategories() : CategoryFoodResult

    @GET("filter.php")
   suspend fun getMealsByCategory(@Query("c") category: String) : MealsResult
}
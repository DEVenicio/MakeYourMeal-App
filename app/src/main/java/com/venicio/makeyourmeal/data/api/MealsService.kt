package com.venicio.makeyourmeal.data.api

import com.venicio.makeyourmeal.data.CategoryResult
import retrofit2.Call
import retrofit2.http.GET

interface MealsService {

    @GET("categories.php")
    fun getAllCategories() : Call<CategoryResult>
}
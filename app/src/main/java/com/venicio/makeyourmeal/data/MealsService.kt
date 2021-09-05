package com.venicio.makeyourmeal.data

import retrofit2.Call
import retrofit2.http.GET

interface MealsService {

    @GET("categories.php")
   fun getAllCategory() : Call <CategoryResult>
}
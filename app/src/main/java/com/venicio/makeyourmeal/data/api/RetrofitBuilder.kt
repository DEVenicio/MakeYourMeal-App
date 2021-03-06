package com.venicio.makeyourmeal.data.api

import com.venicio.makeyourmeal.data.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: MealsService = retrofit.create(MealsService::class.java)


}
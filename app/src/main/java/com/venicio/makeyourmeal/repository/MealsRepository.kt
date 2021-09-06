package com.venicio.makeyourmeal.repository



import com.venicio.makeyourmeal.data.api.MealsService


class MealsRepository(private val service: MealsService) {


    suspend fun getAllCategories() = service.getAllCategories()



}
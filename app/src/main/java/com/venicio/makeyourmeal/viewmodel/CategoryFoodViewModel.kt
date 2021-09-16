package com.venicio.makeyourmeal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venicio.makeyourmeal.data.model.CategoryFoodResult
import com.venicio.makeyourmeal.data.repository.MealsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CategoryFoodViewModel(private val mealsRepository: MealsRepository) : ViewModel() {

    private lateinit var categoryFoodResult: CategoryFoodResult


    private var _listAllCategoriesLiveData = MutableLiveData<CategoryFoodResult>()
    val listAllCategoriesLiveData: LiveData<CategoryFoodResult>
        get() = _listAllCategoriesLiveData


    init {
        _listAllCategoriesLiveData
        getAllCategories()
    }

    private fun getAllCategories() {
        CoroutineScope(IO).launch {
            try {
                categoryFoodResult = mealsRepository.getAllCategories()
                _listAllCategoriesLiveData.postValue(categoryFoodResult)

            } catch (e: Exception) {

            }
        }
    }

    class CategoryViewModelFactory(val mealsRepository: MealsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoryFoodViewModel::class.java)) {
                CategoryFoodViewModel(this.mealsRepository) as T
            } else {
                throw IllegalAccessException("CategoryFoodViewModel Not Found")
            }
        }
    }
}

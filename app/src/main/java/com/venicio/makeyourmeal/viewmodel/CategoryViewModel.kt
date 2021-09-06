package com.venicio.makeyourmeal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venicio.makeyourmeal.data.CategoryResponse
import com.venicio.makeyourmeal.data.CategoryResult
import com.venicio.makeyourmeal.repository.MealsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val mealsRepository: MealsRepository) : ViewModel() {


    private var _listAllCategoriesLiveData = MutableLiveData<CategoryResult>()
    val listAllCategoriesLiveData: LiveData<CategoryResult> = _listAllCategoriesLiveData


    fun init() {
        _listAllCategoriesLiveData
        getAllCategories()

    }

    fun getAllCategories() {
        CoroutineScope(IO).launch {

            val categories = mealsRepository.getAllCategories()
            _listAllCategoriesLiveData.postValue(
                CategoryResult(categories.execute().body()!!.categories)
            )
        }
    }

    class CategoryViewModelFactory(val mealsRepository: MealsRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
                CategoryViewModel(this.mealsRepository) as T
            } else {
                throw IllegalAccessException("ViewModel Not Found")
            }
        }
    }
}

package com.venicio.makeyourmeal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venicio.makeyourmeal.data.Meals
import com.venicio.makeyourmeal.data.MealsResult
import com.venicio.makeyourmeal.repository.MealsRepository
import com.venicio.makeyourmeal.view.ui.MealsByCategoryFragment
import com.venicio.makeyourmeal.view.ui.MealsByCategoryFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import java.lang.Exception

class MealsViewModel(
    private val mealsRepository: MealsRepository,
    private val argumentos: MealsByCategoryFragmentArgs
) :
    ViewModel() {

    private lateinit var mealsResult: MealsResult


    private var _mealsLiveData = MutableLiveData<MealsResult>()
    val mealsLiveData: LiveData<MealsResult>
        get() = _mealsLiveData


    init {
        mealsLiveData
        getMeals(argumentos.categoryMeals)
    }

    private fun getMeals(category: String) {
        CoroutineScope(IO).launch {
            try {
                mealsResult = mealsRepository.getMealsByCategory(category)
                _mealsLiveData.postValue(mealsResult)
            } catch (e: Exception) {

            }
        }
    }

    class MealsViewModelFactory(
        val mealsRepository: MealsRepository,
       val argumentos: MealsByCategoryFragmentArgs
    ) :

        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MealsViewModel(this.mealsRepository, this.argumentos ) as T
        }
    }
}


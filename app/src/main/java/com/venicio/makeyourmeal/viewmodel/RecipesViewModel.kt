package com.venicio.makeyourmeal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.venicio.makeyourmeal.data.model.Ingredient
import com.venicio.makeyourmeal.data.model.RecipesResult
import com.venicio.makeyourmeal.data.repository.MealsRepository
import com.venicio.makeyourmeal.view.ui.RecipesDetailsFragmentArgs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val mealsRepository: MealsRepository,
    private val argumentos: RecipesDetailsFragmentArgs
) :
    ViewModel() {

    private lateinit var recipesResult: RecipesResult

    private var _ListRecipessLiveData = MutableLiveData<RecipesResult>()
    val listRecipesLiveData: LiveData<RecipesResult>
        get() = _ListRecipessLiveData
    var listIngredientLiveData = MutableLiveData<ArrayList<Ingredient>>()


    init {
        listRecipesLiveData
        getRecipes()
        listIngredientLiveData
    }

    private fun getRecipes() {
        CoroutineScope(IO).launch {
            try {
                recipesResult = mealsRepository.getRecipes(argumentos.idRecipes)
                _ListRecipessLiveData.postValue(recipesResult)
                listIngredientLiveData.postValue(recipesResult.meals[0].filterBlankIngredient())
            } catch (e: Exception) {

            }
        }
    }

    class RecipesViewModelFactory(
        val mealsRepository: MealsRepository,
        val argumentos: RecipesDetailsFragmentArgs
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RecipesViewModel(this.mealsRepository, this.argumentos) as T
        }
    }
}

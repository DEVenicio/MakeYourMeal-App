package com.venicio.makeyourmeal.data.model

data class Recipe(
    val dateModified: Any?,
    val idMeal: String?,
    val strArea: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: Any?,
    val strDrinkAlternate: Any?,
    val strImageSource: Any?,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: Any?,
    val strIngredient17: Any?,
    val strIngredient18: Any?,
    val strIngredient19: Any?,
    val strIngredient2: String?,
    val strIngredient20: Any?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: Any?,
    val strMeasure17: Any?,
    val strMeasure18: Any?,
    val strMeasure19: Any?,
    val strMeasure2: String?,
    val strMeasure20: Any?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strSource: Any?,
    val strTags: String?,
    val strYoutube: String?
) {
    private fun getListIngredients(): ArrayList<Ingredient> {

        val ingredient = arrayListOf<Ingredient>()

        ingredient.add(Ingredient(strIngredient1, strMeasure1))
        ingredient.add(Ingredient(strIngredient2, strMeasure2))
        ingredient.add(Ingredient(strIngredient3, strMeasure3))
        ingredient.add(Ingredient(strIngredient4, strMeasure4))
        ingredient.add(Ingredient(strIngredient5, strMeasure5))
        ingredient.add(Ingredient(strIngredient6, strMeasure6))
        ingredient.add(Ingredient(strIngredient7, strMeasure7))
        ingredient.add(Ingredient(strIngredient8, strMeasure8))
        ingredient.add(Ingredient(strIngredient9, strMeasure9))
        ingredient.add(Ingredient(strIngredient10, strMeasure10))
        ingredient.add(Ingredient(strIngredient11, strMeasure11))
        ingredient.add(Ingredient(strIngredient12, strMeasure12))
        ingredient.add(Ingredient(strIngredient13, strMeasure13))
        ingredient.add(Ingredient(strIngredient14, strMeasure14))
        ingredient.add(Ingredient(strIngredient15, strMeasure15))
        ingredient.add(Ingredient(strIngredient16.toString(), strMeasure16.toString()))
        ingredient.add(Ingredient(strIngredient17.toString(), strMeasure17.toString()))
        ingredient.add(Ingredient(strIngredient18.toString(), strMeasure18.toString()))
        ingredient.add(Ingredient(strIngredient19.toString(), strMeasure19.toString()))
        ingredient.add(Ingredient(strIngredient20.toString(), strMeasure20.toString()))
        return ingredient
    }

    fun filterBlankIngredient(): ArrayList<Ingredient> {
        val filteredIngredientList = arrayListOf<Ingredient>()

        val inputIngredientList = getListIngredients()
        inputIngredientList.forEach { itemList ->
            if (!itemList.nameIngredient.isNullOrBlank())
                filteredIngredientList.add(itemList)
        }
        return filteredIngredientList
    }
}





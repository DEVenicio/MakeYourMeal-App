package com.venicio.makeyourmeal.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.venicio.makeyourmeal.view.adapter.RecipesDetailsAdapter
import com.venicio.makeyourmeal.data.api.RetrofitBuilder
import com.venicio.makeyourmeal.data.model.Recipe
import com.venicio.makeyourmeal.databinding.FragmentRecipesDetailsBinding
import com.venicio.makeyourmeal.data.repository.MealsRepository
import com.venicio.makeyourmeal.viewmodel.RecipesViewModel


class RecipesDetailsFragment : Fragment() {

    private lateinit var binding: FragmentRecipesDetailsBinding
    private val argumentos by navArgs<RecipesDetailsFragmentArgs>()
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var recyclerIngredients: RecyclerView
    private val adapter by lazy { RecipesDetailsAdapter() }
    private var getApiInstance = RetrofitBuilder().service
    private var mealsRepository = MealsRepository(getApiInstance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRecipesDetailsBinding.inflate(layoutInflater)

        recyclerIngredients = binding.rvIngredients
        recipesViewModel = RecipesViewModel(mealsRepository, argumentos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return (binding.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerIngredients()
        setupViewModelRecipes()
        setupObserversRecipes()
    }


    private fun setupRecyclerIngredients() {
        recyclerIngredients.setHasFixedSize(true)
        recyclerIngredients.adapter = adapter
    }

    private fun setupViewModelRecipes() {
        recipesViewModel = ViewModelProvider(
            this,
            RecipesViewModel.RecipesViewModelFactory(
                mealsRepository = mealsRepository,
                argumentos = argumentos
            )
        )
            .get(RecipesViewModel::class.java)
    }

    private fun setupObserversRecipes() {
        recipesViewModel.listRecipesLiveData.observe(viewLifecycleOwner,
            Observer {
                val recipe = it.meals[0]
                setupView(recipe)
            })

        recipesViewModel.listIngredientLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setupRecyclerIngredients(it)
        })
    }

    private fun setupView(itemRecipes: Recipe) {

        binding.tvTitleRecipeDetails.text = itemRecipes.strMeal
        binding.tvRecipeInstructions.text = itemRecipes.strInstructions

        Glide.with(binding.root)
            .load(itemRecipes.strMealThumb)
            .into(binding.ivRecipeDetails)
    }
}
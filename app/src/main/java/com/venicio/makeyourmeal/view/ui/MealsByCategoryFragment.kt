package com.venicio.makeyourmeal.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venicio.makeyourmeal.R
import com.venicio.makeyourmeal.adapter.MealsByCategoryAdapter
import com.venicio.makeyourmeal.data.api.RetrofitBuilder
import com.venicio.makeyourmeal.databinding.FragmentMealsbycategoryBinding
import com.venicio.makeyourmeal.repository.MealsRepository
import com.venicio.makeyourmeal.viewmodel.MealsViewModel


class MealsByCategoryFragment : Fragment() {

    private val argumentos by navArgs<MealsByCategoryFragmentArgs>()
    private lateinit var mealsViewModel: MealsViewModel
    private lateinit var binding: FragmentMealsbycategoryBinding
    private val adapter by lazy { MealsByCategoryAdapter() }
    private lateinit var recyclerMeals: RecyclerView
    private var getApiInstance = RetrofitBuilder().service
    private var mealsRepository = MealsRepository(getApiInstance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMealsbycategoryBinding.inflate(layoutInflater)

        recyclerMeals = binding.rvMeals
        mealsViewModel = MealsViewModel(mealsRepository, argumentos)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return (binding.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerMeals()
        setupViewModel()
        setupObservers()
    }


    private fun setupRecyclerMeals() {
        recyclerMeals.layoutManager = LinearLayoutManager(context)
        recyclerMeals.setHasFixedSize(true)
        recyclerMeals.adapter = adapter
    }

    private fun setupViewModel() {
        mealsViewModel = ViewModelProvider(
            this,
            MealsViewModel.MealsViewModelFactory(mealsRepository = mealsRepository, argumentos = argumentos)
        )
            .get(MealsViewModel::class.java)
    }

    private fun setupObservers() {
        mealsViewModel.mealsLiveData.observe(viewLifecycleOwner, Observer {
            adapter.setupRecyclerMeals(it.meals)
        })

    }
}
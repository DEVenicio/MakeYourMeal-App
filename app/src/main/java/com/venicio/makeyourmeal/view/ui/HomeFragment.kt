package com.venicio.makeyourmeal.view.ui


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venicio.makeyourmeal.R
import com.venicio.makeyourmeal.view.adapter.HomeCategoryAdapter
import com.venicio.makeyourmeal.data.api.RetrofitBuilder
import com.venicio.makeyourmeal.databinding.FragmentHomeBinding
import com.venicio.makeyourmeal.data.repository.MealsRepository
import com.venicio.makeyourmeal.viewmodel.CategoryFoodViewModel


class HomeFragment : Fragment() {

    private lateinit var categoryFoodViewModel: CategoryFoodViewModel
    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { HomeCategoryAdapter() }
    private lateinit var recyclerCategory: RecyclerView
    private var getApiInstance = RetrofitBuilder().service
    private var mealsrepository = MealsRepository(getApiInstance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        recyclerCategory = binding.rvCategory
        categoryFoodViewModel = CategoryFoodViewModel(mealsrepository)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return (binding.root)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecycler()
        setupObservers()
        setupViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupRecycler() {
        recyclerCategory.layoutManager = LinearLayoutManager(context)
        recyclerCategory.setHasFixedSize(true)
        recyclerCategory.adapter = adapter
    }

    private fun setupViewModel() {
        categoryFoodViewModel = ViewModelProvider(
            this,
            CategoryFoodViewModel.CategoryViewModelFactory(mealsRepository = mealsrepository)
        )
            .get(CategoryFoodViewModel::class.java)
    }

    private fun setupObservers() {
        categoryFoodViewModel.listAllCategoriesLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvCategory.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            adapter.setupRecyclerView(it.categories)
        })
    }
}



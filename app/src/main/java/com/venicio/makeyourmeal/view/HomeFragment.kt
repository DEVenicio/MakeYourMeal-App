package com.venicio.makeyourmeal.view


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venicio.makeyourmeal.R
import com.venicio.makeyourmeal.adapter.CategoryAdapter
import com.venicio.makeyourmeal.data.api.RetrofitBuilder
import com.venicio.makeyourmeal.databinding.FragmentHomeBinding
import com.venicio.makeyourmeal.repository.MealsRepository
import com.venicio.makeyourmeal.viewmodel.CategoryViewModel


class HomeFragment : Fragment() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var binding: FragmentHomeBinding
    private val adapter by lazy { CategoryAdapter() }
    private lateinit var recycler: RecyclerView
    private var getApiInstance = RetrofitBuilder().service
    private var mealsrepository = MealsRepository(getApiInstance)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        recycler = binding.rvCategory

        categoryViewModel = CategoryViewModel(mealsrepository)

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

        categoryViewModel.init()
        setupRecycler()
        setupObservers()
        setupViewModel()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setupRecycler() {
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter
    }

    private fun setupViewModel() {
        categoryViewModel = ViewModelProvider(
            this,
            CategoryViewModel.CategoryViewModelFactory(mealsRepository = mealsrepository)
        )
            .get(CategoryViewModel::class.java)

    }

    private fun setupObservers() {
        categoryViewModel.listAllCategoriesLiveData.observe(viewLifecycleOwner, Observer {
            binding.rvCategory.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            adapter.setupRecyclerView(it.categories)

        })
    }
}



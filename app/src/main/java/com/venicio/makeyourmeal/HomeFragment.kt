package com.venicio.makeyourmeal

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.util.TimeFormatException
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.venicio.makeyourmeal.adapter.CategoryAdapter
import com.venicio.makeyourmeal.data.CategoryResponse
import com.venicio.makeyourmeal.data.CategoryResult
import com.venicio.makeyourmeal.data.RetrofitBuilder
import com.venicio.makeyourmeal.databinding.FragmentHomeBinding
import kotlinx.coroutines.CoroutineScope
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var recycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)

        recycler = binding.rvCategory

        setHasOptionsMenu(true)

        RetrofitBuilder().service.getAllCategory().enqueue(object : Callback<CategoryResult>{
            override fun onResponse(
                call: Call<CategoryResult>,
                response: Response<CategoryResult>
            ) {
               if (response.isSuccessful){

                   recycler.layoutManager = LinearLayoutManager(context)
                   recycler.setHasFixedSize(true)
                   recycler.adapter = response.body()?.categories?.let { CategoryAdapter(it) }

               }
            }

            override fun onFailure(call: Call<CategoryResult>, t: Throwable) {
                Log.e(TAG, "onFailure: DEU RUIM " + t.message )
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}



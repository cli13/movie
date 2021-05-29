package com.example.movietab.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movietab.MovieApp
import com.example.movietab.R
import com.example.movietab.adapter.MovieAdapter
import com.example.movietab.data.MainRepository
import com.example.movietab.data.remote.MovieApi
import com.example.movietab.databinding.MovieFragmentBinding
import javax.inject.Inject

class MovieFragment : Fragment() {
    companion object {
        const val KEY_ITEM_NUMBER = "item"
        fun newInstance(position: Int): MovieFragment {
            val bundle = Bundle()
            bundle.putInt(KEY_ITEM_NUMBER, position)
            val fragment = MovieFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private var itemNumber: Int? = 0
    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemNumber = arguments?.getInt(KEY_ITEM_NUMBER)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(layoutInflater)
        var factory = (activity?.application as MovieApp).appComponent.getMovieFactoryViewModel()
        viewModel = ViewModelProvider(viewModelStore, factory).get(MovieViewModel::class.java)
        adapter = MovieAdapter(emptyList())
        setUpAdapter()
        setupObservers()
        setupListener()
        return binding.root
    }

    private fun setupListener() {
        binding.btnPrev.setOnClickListener {
            viewModel.decrease()
        }

        binding.btnNext.setOnClickListener {
            viewModel.increase()
        }
    }

    private var maxPage = 10

    private fun setupObservers() {
        viewModel.moviePage.observe(viewLifecycleOwner) {
            binding.btnPrev.isEnabled = it.toInt() != 1
            binding.pageNumber.text = it.toString()
            updateList()
            binding.btnNext.isEnabled = it.toInt() != maxPage
        }
    }

    private fun updateList() {
        when (itemNumber) {
            0 -> {
//                viewModel.getMoviesForPage().observe(viewLifecycleOwner, {
//                    adapter.list = it!!.results
//                    adapter.notifyDataSetChanged()
//                    maxPage = it.total_pages
//                })
                viewModel.fetchData()
                viewModel.liveData.observe(viewLifecycleOwner) {
                    adapter.list = it.results
                    adapter.notifyDataSetChanged()
                    maxPage = it.total_pages
                }
            }
            1 -> {
                viewModel.getPopularMovieForPage().observe(viewLifecycleOwner) {
                    adapter.list = it!!.results
                    adapter.notifyDataSetChanged()
                    maxPage = it.total_pages
                }
            }
        }
    }

    private fun setUpAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.bimobelajar.mymovie.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bimobelajar.mymovie.R
import com.bimobelajar.mymovie.ui.adapter.MovieAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var accountImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(HomeViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        accountImage = view.findViewById(R.id.accountImage)

        homeViewModel.movies.observe(viewLifecycleOwner) { movies ->
            recyclerView.adapter = MovieAdapter(movies) { movie ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(movie.id)
                findNavController().navigate(action)
            }
        }

        accountImage.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }
}

package com.hedaia.preinterviewstcflickerapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.hedaia.preinterviewstcflickerapp.databinding.FragmentPostsListBinding
import com.hedaia.preinterviewstcflickerapp.model.Photo
import com.hedaia.preinterviewstcflickerapp.model.PhotoItem
import com.hedaia.preinterviewstcflickerapp.view.adapters.PostsAdapter
import com.hedaia.preinterviewstcflickerapp.viewmodel.PostsViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class PostsListFragment : Fragment(), PostsAdapter.PostClickInterface {

    lateinit var binding:FragmentPostsListBinding
    val postsViewModel by lazy { ViewModelProvider(this)[PostsViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding =FragmentPostsListBinding.inflate(layoutInflater)

        val adapter = PostsAdapter(this)
        binding.postsRv.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        binding.postsRv.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshPostsList()
        }

        postsViewModel.getPosts().observe(viewLifecycleOwner){
           adapter.submitData(viewLifecycleOwner.lifecycle,it)

        }


        return binding.root
    }

    fun refreshPostsList(){
        binding.swipeRefreshLayout.isRefreshing = true
        postsViewModel.getPosts()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    override fun onPostClick(photo: Photo) {
        val photoItem = PhotoItem(photo.id,photo.server,photo.secret,photo.title)
        val action = PostsListFragmentDirections.actionPostsListFragmentToPostDetailFragment(photoItem)
        findNavController().navigate(action)
    }



}
package com.hedaia.preinterviewstcflickerapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.hedaia.preinterviewstcflickerapp.model.PhotoItem
import com.hedaia.preinterviewstcflickerapp.databinding.FragmentPostDetailBinding
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class PostDetailFragment : Fragment() {

    lateinit var binding: FragmentPostDetailBinding
    val args: PostDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentPostDetailBinding.inflate(layoutInflater)

        val post = args.post
        val title = post.title
        val serverID = post.server
        val secret = post.secret
        val id = post.id
        val photoLink = "https://live.staticflickr.com/$serverID/${id}_${secret}.jpg"

        binding.titleTv.setText(title)

        Glide.with(requireContext()).load(photoLink).into(binding.photoIv)


        return binding.root
    }


}
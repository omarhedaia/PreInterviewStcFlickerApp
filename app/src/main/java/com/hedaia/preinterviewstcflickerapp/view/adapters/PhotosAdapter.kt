package com.hedaia.preinterviewstcflickerapp.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hedaia.preinterviewstcflickerapp.databinding.PostItemRowBinding
import com.hedaia.preinterviewstcflickerapp.model.Photo
import com.hedaia.preinterviewstcflickerapp.model.PhotoItem

class PostsAdapter(val postClickInterface: PostClickInterface):
    PagingDataAdapter<Photo, PostsAdapter.ViewHolder>(
    PhotoDiffUtil()
) {
    class ViewHolder(var binding: PostItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PostItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val post = getItem(position)
        if (post != null)
        {
            val title = post.title
            holder.binding.apply {

                postTitleTv.setText(title)

                postTitleTv.setOnClickListener{

                        postClickInterface.onPostClick(post)

                }

            }
        }





    }

    interface PostClickInterface{
        fun onPostClick(photoItem: Photo)
    }


}
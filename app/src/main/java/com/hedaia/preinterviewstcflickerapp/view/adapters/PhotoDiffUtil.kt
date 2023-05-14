package com.hedaia.preinterviewstcflickerapp.view.adapters

import androidx.recyclerview.widget.DiffUtil
import com.hedaia.preinterviewstcflickerapp.model.Photo
import com.hedaia.preinterviewstcflickerapp.model.PhotoItem

class PhotoDiffUtil(): DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem == newItem
    }
}
package com.hedaia.preinterviewstcflickerapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.hedaia.preinterviewstcflickerapp.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class PostsViewModel @Inject constructor(var postsRepository: PostsRepository): ViewModel() {

    fun getPosts():LiveData<PagingData<Photo>>{
        return postsRepository.getPosts().cachedIn(viewModelScope)
    }

}



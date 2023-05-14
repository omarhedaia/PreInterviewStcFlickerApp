package com.hedaia.preinterviewstcflickerapp.model

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface Repository {

    fun getPosts():LiveData<PagingData<Photo>>
}
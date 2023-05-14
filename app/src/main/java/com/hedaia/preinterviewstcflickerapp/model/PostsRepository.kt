package com.hedaia.preinterviewstcflickerapp.model


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import javax.inject.Inject

class PostsRepository @Inject constructor(var apiInterface: APIInterface):Repository {

    override fun getPosts() = Pager(
        pagingSourceFactory = {PostPagingSource(apiInterface)},
        config = PagingConfig(
            pageSize = 20,
            maxSize = 100
            )
    ).liveData


}
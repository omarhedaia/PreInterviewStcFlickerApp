package com.hedaia.preinterviewstcflickerapp.model


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hedaia.preinterviewstcflickerapp.view.activities.PostsHomeActivity
import retrofit2.HttpException
import java.io.IOException



private const val FIRST_PAGE = 1;

class PostPagingSource (var apiInterface: APIInterface) : PagingSource<Int, Photo>() {

    private val API_KEY = "e905300630fa2ae54e8171fed3020d82"
    private val METHOD = "flickr.photos.getRecent"
    private val FORMAT = "json"

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        val page = params.key ?: FIRST_PAGE

        return try {

            val response = apiInterface.getPostsItem(METHOD, API_KEY, params.loadSize, page, FORMAT, "1")
            val data = response.body()
            var postList = emptyList<Photo>()
            if (data!=null)
            {
                postList = data.photos.photo
            }

            LoadResult.Page(
                data = postList,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = if (postList.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}


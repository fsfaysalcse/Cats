package com.faysal.androidmvvmp3.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.faysal.androidmvvmp3.api.CatsAPI
import com.faysal.androidmvvmp3.models.Cat
import com.faysal.androidmvvmp3.utility.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CatsDataSource @Inject constructor(
    private val catsAPI : CatsAPI
) : PagingSource<Int, Cat>(){
    override fun getRefreshKey(state: PagingState<Int, Cat>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Cat> {
       val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = catsAPI.getAllCats(params.loadSize,page)
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = page + 1
            )
        }catch (e : IOException){
            val error = IOException("Please check Internet Connection")
            LoadResult.Error(error)
        }catch (e : HttpException){
            LoadResult.Error(e)
        }
    }

}
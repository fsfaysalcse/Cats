package com.faysal.androidmvvmp3.data.repository

import androidx.paging.*
import com.faysal.androidmvvmp3.data.datasource.CatsDataSource
import com.faysal.androidmvvmp3.data.local.database.AppDatabase
import com.faysal.androidmvvmp3.data.mediators.CatRemoteMediators
import com.faysal.androidmvvmp3.data.remote.api.CatsAPI
import com.faysal.androidmvvmp3.models.Cat
import com.faysal.androidmvvmp3.models.Cats
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(
    private val cateAPI: CatsAPI,
    private val database: AppDatabase
) {
    private val pagingDataSourceFactory = {database.catDao.getCats()}

    /*@ExperimentalPagingApi
    fun getCats() : Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = CatRemoteMediators(
                cateAPI,
                database
            ),
            pagingSourceFactory = pagingDataSourceFactory
        ).flow
    }*/

   @ExperimentalPagingApi
    fun getCats() : Flow<PagingData<Cat>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory ={
                CatsDataSource(cateAPI)
            }
        ).flow
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 10
    }
}
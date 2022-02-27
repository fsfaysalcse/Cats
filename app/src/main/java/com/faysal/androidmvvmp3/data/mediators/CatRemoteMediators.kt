package com.faysal.androidmvvmp3.data.mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.faysal.androidmvvmp3.data.remote.api.CatsAPI
import com.faysal.androidmvvmp3.data.local.database.AppDatabase
import com.faysal.androidmvvmp3.data.local.entity.RemoteKey
import com.faysal.androidmvvmp3.models.Cat
import com.faysal.androidmvvmp3.models.Cats
import com.faysal.androidmvvmp3.utility.STARTING_PAGE_INDEX
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class CatRemoteMediators (
    private val api: CatsAPI,
    private val database: AppDatabase

) : RemoteMediator<Int, Cat>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Cat>): MediatorResult {
        val key = when (loadType) {
            LoadType.REFRESH -> {
                if (database.catDao.totalAllCats() > 0) return MediatorResult.Success(false)
                null
            }
            LoadType.PREPEND -> {
                return MediatorResult.Success(true)
            }
            LoadType.APPEND -> {
                getRemoteKey()
            }
        }

        try {
            if (key != null) {
                if (key.isFinished) return MediatorResult.Success(true)
            }
            val page : Int = key?.nextKey ?: STARTING_PAGE_INDEX
            val catsList = api.getAllCats(state.config.pageSize,page)

            val endOfPaginationReached = catsList.size == 0

            database.withTransaction {
                val nextKey = page + 1

                database.remoteDao.insertKey(
                   RemoteKey(
                       id = 0,
                       nextKey = nextKey,
                       isFinished = endOfPaginationReached
                   )
                )
                database.catDao.insertAllCats(catsList)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKey(): RemoteKey? {
        return database.remoteDao.getRemoteKeys().firstOrNull()
    }

}
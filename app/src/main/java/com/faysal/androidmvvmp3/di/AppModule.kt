package com.faysal.androidmvvmp3.di

import android.content.Context
import com.faysal.androidmvvmp3.api.CatsAPI
import com.faysal.androidmvvmp3.data.local.dao.CatDao
import com.faysal.androidmvvmp3.data.local.dao.RemoteKeysDao
import com.faysal.androidmvvmp3.data.local.database.AppDatabase
import com.faysal.androidmvvmp3.utility.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.invoke(context)
    }

    @Provides
    @Singleton
    fun providesKeyDao(appDatabase: AppDatabase) : RemoteKeysDao = appDatabase.remoteKey

    @Provides
    @Singleton
    fun providesCatDao(appDatabase: AppDatabase) : CatDao = appDatabase.catDao

    @Provides
    @Singleton
    fun providesRetrofit() : Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesCatApi(retrofit: Retrofit) : CatsAPI = retrofit.create(CatsAPI::class.java)

}
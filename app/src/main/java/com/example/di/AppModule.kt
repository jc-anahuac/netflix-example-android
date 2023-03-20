package com.example.di

import android.content.Context
import com.example.data.Local.AppDatabase
import com.example.data.Local.dao.MovieDao
import com.example.data.remote.ApiService
import com.example.data.remote.RemoteDataSource
import com.example.data.repository.MovieRepository
import com.example.utils.Const
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl(Const.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun providesRemoteDataSource(apiService: ApiService) = RemoteDataSource(apiService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()

    @Singleton
    @Provides
    fun provideMovieRepository(localDataSource: MovieDao, remoteDataSource: RemoteDataSource): MovieRepository =
        MovieRepository(localDataSource, remoteDataSource)
}
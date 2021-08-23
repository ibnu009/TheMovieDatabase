package com.ibnu.themoviedatabase.core.di.module

import android.content.Context
import androidx.room.Room
import com.ibnu.themoviedatabase.core.data.local.room.MovieCatalogueDatabase
import com.ibnu.themoviedatabase.core.data.local.room.MovieDao
import com.ibnu.themoviedatabase.core.utils.ConstValue.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieCatalogueDatabase =
        Room.databaseBuilder(
            context,
            MovieCatalogueDatabase::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMovieDao(database: MovieCatalogueDatabase): MovieDao = database.getMovieDao()

}
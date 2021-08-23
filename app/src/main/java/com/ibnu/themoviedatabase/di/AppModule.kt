package com.ibnu.themoviedatabase.di

import com.ibnu.themoviedatabase.core.domain.usescase.MovieUseCase
import com.ibnu.themoviedatabase.core.domain.usescase.MovieUserCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMovieUseCase(movieUserCaseImpl: MovieUserCaseImpl): MovieUseCase

}
package com.ibnu.themoviedatabase.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ibnu.themoviedatabase.core.data.local.entities.MovieEntity
import com.ibnu.themoviedatabase.core.utils.GenreConverter

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreConverter::class)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun getMovieDao(): MovieDao

}
package com.example.data.Local;
import android.content.Context
import androidx.room.Database;
import androidx.room.Room
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters
import com.example.data.Local.dao.GenreDao
import com.example.data.Local.dao.MovieDao
import com.example.data.entities.Converters
import com.example.data.entities.GenreEntity
import com.example.data.entities.MovieEntity

import kotlin.jvm.Volatile;

@Database(
    entities = [
        MovieEntity::class,
        GenreEntity::class
               ],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
        abstract fun movieDao(): MovieDao
        abstract fun genreDao(): GenreDao

        companion object {
            @Volatile
            private var instance: AppDatabase? = null

            fun getDatabase(context: Context): AppDatabase =
                instance ?: synchronized(this) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            private fun buildDatabase(appContext:Context)=
                Room.databaseBuilder(appContext, AppDatabase::class.java,"movies_database")
                    .fallbackToDestructiveMigration()
                    .build()
        }


    }


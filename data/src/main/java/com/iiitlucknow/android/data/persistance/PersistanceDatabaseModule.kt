package com.iiitlucknow.android.data.persistance

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object PersistanceDatabaseModule {

    @Provides
    fun providePersistanceDatabase(@ApplicationContext applicationContext: Context): PersistanceDatabase {
        return Room.databaseBuilder(
            applicationContext,
            PersistanceDatabase::class.java,
            "item_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}
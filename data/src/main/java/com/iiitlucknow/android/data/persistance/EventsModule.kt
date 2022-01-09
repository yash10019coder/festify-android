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
object EventsModule {

    @Provides
    fun provideEventsRepository(dao: EventsDao): EventsRepository {
        return EventsRepository(dao)
    }

    @Provides
    fun provideEventsDatabase(@ApplicationContext context: Context): EventsDatabase {
        return Room.databaseBuilder(
            context,
            EventsDatabase::class.java,
            "item_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

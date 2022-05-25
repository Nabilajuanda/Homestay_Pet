package org.d3if4056.homestaypet.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi

@Database(entities = [PetEntity::class], version = 1, exportSchema = false)
abstract class PetDb: RoomDatabase() {

    abstract val dao: PetDao

    companion object {

        @Volatile
        private var INSTANCE: PetDb? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): PetDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PetDb::class.java,
                        "pet.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
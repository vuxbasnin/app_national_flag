package com.base.basemvvm.data.local.room_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.base.basemvvm.data.local.dao.NationalFlagDao
import com.base.basemvvm.data.local.room_database.NationalFlagRoomDatabase.Companion.VERSION_DATABASE
import com.base.basemvvm.data.model.response.flag.NationalFlagResponseItem
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@Singleton
@Database(entities = [NationalFlagResponseItem::class], version = VERSION_DATABASE)
abstract class NationalFlagRoomDatabase : RoomDatabase() {
    abstract fun nationalFlagDao(): NationalFlagDao

    companion object {
        const val VERSION_DATABASE = 1

        @Volatile
        private var INSTANCE: NationalFlagRoomDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): NationalFlagRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NationalFlagRoomDatabase::class.java,
                    "national_flag_response_item"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(NationalDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class NationalDatabaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.nationalFlagDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(wordDao: NationalFlagDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            wordDao.deleteAll()
        }
    }
}
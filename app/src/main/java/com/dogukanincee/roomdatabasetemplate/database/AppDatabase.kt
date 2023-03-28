package com.dogukanincee.roomdatabasetemplate.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dogukanincee.roomdatabasetemplate.dao.UserDao
import com.dogukanincee.roomdatabasetemplate.data.User

/**
 * AppDatabase class represents the Room database for the application.
 * It contains a single table named "users" which holds instances of the User entity.
 */
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    /**
     * Abstract method that returns an instance of the UserDao interface.
     * This method is used by clients to get access to the User table in the database.
     */
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Returns an instance of the AppDatabase class.
         * If an instance already exists, it returns that instance.
         * If an instance doesn't exist, it creates a new one using the Room.databaseBuilder() method.
         *
         * @param context the context of the application
         * @return an instance of the AppDatabase class
         */
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
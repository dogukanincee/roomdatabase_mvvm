package com.dogukanincee.roomdatabasetemplate.repository

import android.util.Log
import com.dogukanincee.roomdatabasetemplate.dao.UserDao
import com.dogukanincee.roomdatabasetemplate.data.User
import kotlinx.coroutines.flow.Flow

/**
 * Repository class that interacts with the [UserDao] to perform database operations.
 */
class UserRepository(private val userDao: UserDao) {

    /**
     * Returns a [Flow] that emits a list of all users in the database.
     */
    fun getAllUsers(): Flow<List<User>> {
        Log.i(TAG, "Getting all users from database.")
        return userDao.getAllUsers()
    }

    /**
     * Returns the user with the specified [id] from the database, or null if not found.
     */
    fun getUserById(id: Int): User? {
        Log.i(TAG, "Getting user with id=$id from database.")
        return userDao.getUserById(id)
    }

    /**
     * Inserts the specified [user] into the database.
     */
    fun insertUser(user: User) {
        Log.i(TAG, "Inserting user into database: $user")
        userDao.insertUser(user)
    }

    /**
     * Updates the specified [user] in the database.
     */
    fun updateUser(user: User) {
        Log.i(TAG, "Updating user in database: $user")
        userDao.updateUser(user)
    }

    /**
     * Deletes the specified [user] from the database.
     */
    suspend fun deleteUser(user: User) {
        Log.i(TAG, "Deleting user from database: $user")
        userDao.deleteUser(user)
    }

    companion object {
        private const val TAG = "UserRepository"
    }
}

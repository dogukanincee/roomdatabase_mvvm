package com.dogukanincee.roomdatabasetemplate.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.dogukanincee.roomdatabasetemplate.data.User
import com.dogukanincee.roomdatabasetemplate.database.AppDatabase
import com.dogukanincee.roomdatabasetemplate.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * ViewModel for handling user-related operations in the app.
 *
 * @property application The application context.
 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository
    private val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.getAllUsers().asLiveData()
    }

    /**
     * Gets all users from the repository as a LiveData object.
     *
     * @return A LiveData object containing a list of all users in the repository.
     */
    fun getAllUsers(): LiveData<List<User>> {
        return allUsers
    }

    /**
     * Gets a user by their ID from the repository.
     *
     * @param id The ID of the user to get.
     * @return The User object with the given ID, or null if it doesn't exist.
     */
    fun getUserById(id: Int): User? {
        return repository.getUserById(id)
    }

    /**
     * Inserts a new user into the repository.
     *
     * @param user The user object to insert.
     */
    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
            Log.i(TAG, "Inserted user: $user")
        }
    }

    /**
     * Updates an existing user in the repository.
     *
     * @param user The user object to update.
     */
    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
            Log.i(TAG, "Updated user: $user")
        }
    }

    /**
     * Deletes a user from the repository.
     *
     * @param user The user object to delete.
     */
    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
            Log.i(TAG, "Deleted user: $user")
        }
    }

    companion object {
        private const val TAG = "UserViewModel"
    }
}
package com.dogukanincee.roomdatabasetemplate

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dogukanincee.roomdatabasetemplate.dao.UserDao
import com.dogukanincee.roomdatabasetemplate.data.User
import com.dogukanincee.roomdatabasetemplate.database.AppDatabase
import com.dogukanincee.roomdatabasetemplate.repository.UserRepository
import com.dogukanincee.roomdatabasetemplate.view_model.UserViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.assertNotNull
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class UserViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase
    private lateinit var userRepository: UserRepository
    private lateinit var userViewModel: UserViewModel

    @Before
    fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        db = Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java).build()
        userDao = db.userDao()
        userRepository = UserRepository(userDao)
        userViewModel = UserViewModel(application)

        assertNotNull(application)
        assertNotNull(db)
        assertNotNull(userDao)
        assertNotNull(userRepository)
        assertNotNull(userViewModel)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetUserById() {
        userViewModel.viewModelScope.launch(Dispatchers.IO) {
            // Insert a new user
            val user = User(1, "John", 10)
            userRepository.insertUser(user)

            // Get the inserted user by ID using the view model
            val insertedUser = userViewModel.getUserById(user.id)

            // Verify that the inserted user is retrieved correctly
            assertEquals(user.id, insertedUser?.id)
            assertEquals(user.name, insertedUser?.name)
            assertEquals(user.age, insertedUser?.age)
        }
    }

    @Test
    fun getAllUsers() {
        userViewModel.viewModelScope.launch(Dispatchers.IO) {
            // Insert some users
            val user1 = User(1, "John", 10)
            val user2 = User(2, "Bob", 12)
            userRepository.insertUser(user1)
            userRepository.insertUser(user2)

            // Get all users using the view model
            val allUsers = userViewModel.getAllUsers().getOrAwaitValue()

            // Verify that all users are retrieved correctly
            assertEquals(2, allUsers.size)
            assertEquals(user1.name, allUsers[0].name)
            assertEquals(user2.name, allUsers[1].name)
        }
    }

    @Test
    fun updateAndDeleteUser() {
        userViewModel.viewModelScope.launch(Dispatchers.IO) {
            // Insert a new user
            val user = User(1, "Doe", 10)
            userRepository.insertUser(user)

            // Update the user using the view model
            val updatedUser = User(user.id, "Bob", 10)
            userViewModel.updateUser(updatedUser)

            // Get the updated user by ID using the view model
            val retrievedUser = userViewModel.getUserById(user.id)

            // Verify that the user was updated correctly
            assertEquals(updatedUser.name, retrievedUser?.name)
            assertEquals(updatedUser.name, retrievedUser?.name)

            // Delete the user using the view model
            userViewModel.deleteUser(updatedUser)

            // Get the deleted user by ID using the view model
            val deletedUser = userViewModel.getUserById(user.id)

            // Verify that the user was deleted correctly
            assertEquals(null, deletedUser)
        }
    }

    /**
     * Gets the value of a LiveData object without blocking the main thread.
     *
     * This function uses a CountDownLatch to wait until the LiveData object is updated, and then returns
     * the updated value.
     *
     * @param T The type of the LiveData object.
     * @return The updated value of the LiveData object.
     */
    fun <T> LiveData<T>.getOrAwaitValue(): T {
        var returnValue: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(value: T) {
                returnValue = value
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)
        return returnValue as T
    }
}
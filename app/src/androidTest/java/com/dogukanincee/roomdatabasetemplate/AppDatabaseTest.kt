package com.dogukanincee.roomdatabasetemplate

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dogukanincee.roomdatabasetemplate.database.AppDatabase
import org.junit.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dogukanincee.roomdatabasetemplate.dao.UserDao
import com.dogukanincee.roomdatabasetemplate.data.User
import org.junit.Assert.assertEquals
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun setUp() {
        val application = ApplicationProvider.getApplicationContext<Application>()
        db = Room.inMemoryDatabaseBuilder(application, AppDatabase::class.java).build()
        userDao = db.userDao()

        Assert.assertNotNull(application)
        Assert.assertNotNull(db)
        Assert.assertNotNull(userDao)
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertAndGetUser() {
        // Insert a new user into the database
        val user = User(1, "John", 10)
        userDao.insertUser(user)

        // Get the inserted user from the database
        val insertedUser = userDao.getUserById(user.id)

        // Verify that the inserted user is retrieved correctly
        assertEquals(user.id, insertedUser?.id)
        assertEquals(user.name, insertedUser?.name)
        assertEquals(user.age, insertedUser?.age)
    }
}

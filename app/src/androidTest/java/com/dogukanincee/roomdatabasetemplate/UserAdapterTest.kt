package com.dogukanincee.roomdatabasetemplate

import com.dogukanincee.roomdatabasetemplate.view.UserAdapter
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dogukanincee.roomdatabasetemplate.data.User
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserAdapterTest {

    private lateinit var userAdapter: UserAdapter

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        userAdapter = UserAdapter(context)
    }

    @Test
    fun getItemCount_returnsCorrectCount() {
        // Set the list of users to the adapter
        val userList = listOf(
            User(1, "John", 25),
            User(2, "Jane", 30),
            User(3, "Bob", 40)
        )
        userAdapter.setUsers(userList)

        // Verify that the adapter returns the correct item count
        assertEquals(userList.size, userAdapter.itemCount)
    }
}

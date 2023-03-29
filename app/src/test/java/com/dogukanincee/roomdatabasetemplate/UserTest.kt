package com.dogukanincee.roomdatabasetemplate

import com.dogukanincee.roomdatabasetemplate.data.User
import org.junit.Assert.assertEquals
import org.junit.Test

class UserTest {

    @Test
    fun testToString() {
        val user = User(1, "John", 30)
        assertEquals("User(id=1, name=John, age=30)", user.toString())
    }

    @Test
    fun testGetters() {
        val user = User(1, "John", 30)
        assertEquals(1, user.id)
        assertEquals("John", user.name)
        assertEquals(30, user.age)
    }
}

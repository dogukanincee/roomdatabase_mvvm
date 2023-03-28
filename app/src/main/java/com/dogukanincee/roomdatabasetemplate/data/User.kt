package com.dogukanincee.roomdatabasetemplate.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class representing a user entity to be stored in the database.
 * @property id the unique identifier of the user.
 * @property name the name of the user.
 * @property age the age of the user.
 */
@Entity(tableName = "users")
data class User(
    @PrimaryKey val id: Int,
    val name: String,
    val age: Int
) {

    /**
     * Returns a string representation of the user object.
     * @return the string representation of the user object.
     */
    override fun toString(): String {
        return "User(id=$id, name=$name, age=$age)"
    }
}
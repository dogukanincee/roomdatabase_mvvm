package com.dogukanincee.roomdatabasetemplate.dao;

import androidx.room.*;
import com.dogukanincee.roomdatabasetemplate.data.User;
import kotlinx.coroutines.flow.Flow;

/**
 * Data access object (DAO) for User entity
 */
@Dao
interface UserDao {

    /**
     * Retrieves all users from the database
     *
     * @return Flow of List of User entities
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>;

    /**
     * Retrieves a user from the database by their ID
     *
     * @param id the ID of the user to retrieve
     * @return the User entity or null if not found
     */
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): User?;

    /**
     * Inserts a user entity into the database
     *
     * @param user the User entity to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User);

    /**
     * Updates a user entity in the database
     *
     * @param user the User entity to update
     */
    @Update
    fun updateUser(user: User);

    /**
     * Deletes a user entity from the database
     *
     * @param user the User entity to delete
     */
    @Delete
    fun deleteUser(user: User);
}

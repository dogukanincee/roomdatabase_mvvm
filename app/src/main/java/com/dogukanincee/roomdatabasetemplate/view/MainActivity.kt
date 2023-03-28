package com.dogukanincee.roomdatabasetemplate.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dogukanincee.roomdatabasetemplate.R
import com.dogukanincee.roomdatabasetemplate.data.User
import com.dogukanincee.roomdatabasetemplate.view_model.UserViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter

    /**
     * Initializes the activity and sets up the RecyclerView with the adapter.
     * Also adds some users to the database for testing and observes the list of users.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate the UserViewModel
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        // Set up the RecyclerView
        recyclerView = findViewById(R.id.recyclerview)
        adapter = UserAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Add some users to the database for testing
        userViewModel.insertUser(User(1, "Bob", 40))
        userViewModel.insertUser(User(2, "Alice", 30))
        userViewModel.insertUser(User(3, "Charlie", 25))
        userViewModel.insertUser(User(4, "David", 35))
        userViewModel.insertUser(User(5, "Emily", 28))
        userViewModel.insertUser(User(6, "Frank", 42))
        userViewModel.insertUser(User(7, "Grace", 29))
        userViewModel.insertUser(User(8, "Henry", 31))
        userViewModel.insertUser(User(9, "Isabel", 27))
        userViewModel.insertUser(User(10, "John", 38))

        // Observe the list of users
        userViewModel.getAllUsers().observe(this) { users ->
            // Update the UI with the list of users
            adapter.setUsers(users)
            // Log the updated list of users
            users.forEach { user ->
                Log.i(TAG, "User ID: ${user.id}, Name: ${user.name}, Age: ${user.age}")
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
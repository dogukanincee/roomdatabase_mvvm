package com.dogukanincee.roomdatabasetemplate.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dogukanincee.roomdatabasetemplate.R
import com.dogukanincee.roomdatabasetemplate.data.User

/**
 * RecyclerView adapter for displaying a list of users.
 */
class UserAdapter(context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.name_textview)
        val ageTextView: TextView = itemView.findViewById(R.id.age_textview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = inflater.inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = users[position]
        holder.nameTextView.text = currentUser.name
        holder.ageTextView.text = currentUser.age.toString()

        // Log the user's name and age for debugging purposes
        Log.i(
            TAG,
            "onBindViewHolder: User ${currentUser.id}: ${currentUser.name}, ${currentUser.age}"
        )
    }

    override fun getItemCount(): Int {
        return users.size
    }

    /**
     * Sets the list of users to be displayed in the RecyclerView.
     * Notifies the adapter of the data set change.
     *
     * @param users the list of users to be displayed
     */
    fun setUsers(users: List<User>) {
        val oldSize = this.users.size
        this.users = users
        notifyItemRangeInserted(oldSize, users.size - oldSize)
    }

    /**
     * Removes a user from the list at the specified position.
     * Notifies the adapter of the data set change.
     *
     * @param position the position of the user to be removed
     */
    fun removeUser(position: Int) {
        users = users.filterIndexed { index, _ -> index != position }
        notifyItemRangeRemoved(position, 1)
    }

    /**
     * Updates a user in the list at the specified position.
     * Notifies the adapter of the data set change.
     *
     * @param position the position of the user to be updated
     * @param user the updated user object
     */
    fun updateUser(position: Int, user: User) {
        users = users.mapIndexed { index, oldUser ->
            if (index == position) user else oldUser
        }
        notifyItemRangeChanged(position, 1)
    }

    companion object {
        private const val TAG = "UserAdapter"
    }
}
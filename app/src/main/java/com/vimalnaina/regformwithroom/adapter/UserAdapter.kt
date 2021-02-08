package com.vimalnaina.regformwithroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vimalnaina.regformwithroom.R
import com.vimalnaina.regformwithroom.db.User
import kotlinx.android.synthetic.main.item_users.view.*

class UserAdapter(
    var users: List<User>
) : RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_users, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var currentUser = users[position]
        holder.itemView.apply {
            tvSFname.text = currentUser.fname
            tvSLname.text = currentUser.lname
            tvSEmail.text = currentUser.email
            tvSPhone.text = currentUser.phone
            tvSPass.text = currentUser.password
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

}
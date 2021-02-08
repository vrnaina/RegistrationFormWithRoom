package com.vimalnaina.regformwithroom.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.vimalnaina.regformwithroom.db.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM User ORDER BY uid DESC")
    suspend fun getAllUsers() : List<User>

    @Query("SELECT password FROM User WHERE email = :userEmail")
    suspend fun getUserPass(userEmail: String) : String?
}
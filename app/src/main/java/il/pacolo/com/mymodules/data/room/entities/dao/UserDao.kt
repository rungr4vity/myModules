package il.pacolo.com.mymodules.data.room.entities.dao

import androidx.room.Insert
import androidx.room.Query
import il.pacolo.com.mymodules.data.room.entities.User

interface UserDao {

    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getAllUsers(id: Int): List<User>

}
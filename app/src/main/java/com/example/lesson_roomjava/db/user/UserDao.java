package com.example.lesson_roomjava.db.user;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

// Интерфейс для доступа к данным пользователей в базе данных
@Dao
public interface UserDao {
    // Метод для вставки пользователя в базу данных
    @Insert
    void insert(User user);

    // Метод для удаления пользователя по ID
    @Query("DELETE FROM user_table WHERE id = :userId")
    void deleteById(int userId);

    // Метод для получения всех пользователей из базы данных
    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    // Метод для поиска пользователя по ID
    @Query("SELECT * FROM user_table WHERE id = :userId")
    LiveData<User> findUserById(int userId);
}

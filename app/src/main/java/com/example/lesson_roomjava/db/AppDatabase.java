package com.example.lesson_roomjava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lesson_roomjava.db.user.User;
import com.example.lesson_roomjava.db.user.UserDao;

// Абстрактный класс базы данных Room
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    // Метод для получения объекта доступа к данным для сущности User
    public abstract UserDao userDao();
}

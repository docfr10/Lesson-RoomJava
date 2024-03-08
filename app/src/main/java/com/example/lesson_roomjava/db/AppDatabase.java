package com.example.lesson_roomjava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lesson_roomjava.db.user.User;
import com.example.lesson_roomjava.db.user.UserDao;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}

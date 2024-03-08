package com.example.lesson_roomjava.db.user;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lesson_roomjava.db.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private ExecutorService executorService;

    public UserRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public LiveData<User> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}

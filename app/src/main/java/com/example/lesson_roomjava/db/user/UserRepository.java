package com.example.lesson_roomjava.db.user;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.lesson_roomjava.db.AppDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Класс для работы с данными пользователей в базе данных
public class UserRepository {
    // Объект доступа к данным пользователя
    private final UserDao userDao;
    // LiveData для всех пользователей
    private final LiveData<List<User>> allUsers;
    // Пул потоков для выполнения асинхронных операций
    private final ExecutorService executorService;

    // Конструктор класса
    public UserRepository(Application application) {
        // Инициализация базы данных Room и UserDao
        AppDatabase db = Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        userDao = db.userDao();
        // Получение LiveData для всех пользователей
        allUsers = userDao.getAllUsers();
        // Инициализация пула потоков
        executorService = Executors.newFixedThreadPool(2);
    }

    // Метод для вставки пользователя в базу данных
    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        executorService.execute(() -> userDao.deleteById(userId));
    }

    // Метод для получения LiveData всех пользователей
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<User> findUserById(int userId) {
        return userDao.findUserById(userId);
    }
}

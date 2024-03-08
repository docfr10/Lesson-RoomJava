package com.example.lesson_roomjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lesson_roomjava.db.user.User;
import com.example.lesson_roomjava.db.user.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    // Репозиторий и LiveData для пользователей
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    // Конструктор класса
    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    // Метод для добавления пользователя
    public void insert(User user) {
        repository.insert(user);
    }

    // Метод для удаления пользователя по ID
    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    // Метод для получения всех пользователей
    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    // Метод для поиска пользователя по ID
    public LiveData<User> findUserById(int userId) {
        return repository.findUserById(userId);
    }
}

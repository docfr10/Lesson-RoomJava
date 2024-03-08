package com.example.lesson_roomjava.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lesson_roomjava.db.user.User;
import com.example.lesson_roomjava.db.user.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository repository;
    private LiveData<List<User>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void deleteById(int userId) {
        repository.deleteById(userId);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
}

